package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"path/filepath"
	"strings"

	flag "github.com/spf13/pflag"
)

const (
	HOME_NAME   = "POCHI_HOME"
	BREW_PATH   = "/usr/local/opt/pochi"
	PROG_NAME   = "pochi"
	CONFIG_PATH = "POCHI_CONFIG_PATH"
	LIB_DIR     = "distribution/target/lib"
)

type options struct {
	classpath  string
	config     string
	expression string
	helpFlag   bool
	args       []string
}

func helpMessage(prog string) string {
	return fmt.Sprintf(`%s [OPTIONS] [SCRIPT_FILE [ARGV...]]
OPTIONS
    -c, --classpath <CLASSPATH>      specifies classpath for Groovy (JVM)
    -C, --config <CONFIG_FILE>       specifies configuration file.
    -e, --expression <EXPRESSION>    specifies command line script.

    -h, --help                       print this message.
SCRIPT_FILE ARGV
    Groovy script file name and its arguments.`, prog)
}

func ExistsDir(path string) bool {
	info, err := os.Stat(path)
	return err == nil && info.IsDir()
}

func pochiHome() string {
	generators := []func() string{
		func() string { return os.Getenv(HOME_NAME) },
		func() string { return "../pochi" },
		func() string { return BREW_PATH },
	}
	for _, generator := range generators {
		path := generator()
		if path != "" && ExistsDir(path) {
			return path
		}
	}
	wd, _ := os.Getwd()
	return wd
}

func buildFlagSet() (*flag.FlagSet, *options) {
	var opts = new(options)
	var flags = flag.NewFlagSet(PROG_NAME, flag.ContinueOnError)
	flags.Usage = func() { fmt.Println(helpMessage(PROG_NAME)) }
	flags.BoolVarP(&opts.helpFlag, "help", "h", false, "print this message")
	flags.StringVarP(&opts.classpath, "classpath", "c", "", "specifies classpath for Groovy (JVM)")
	flags.StringVarP(&opts.config, "config", "C", "", "specifies command line script")
	flags.StringVarP(&opts.expression, "expression", "e", "", "specifies configuration file")
	return flags, opts
}

func parseArguments(args []string) (*options, error) {
	flags, opts := buildFlagSet()
	if err := flags.Parse(args); err != nil {
		return nil, err
	}
	opts.args = flags.Args()[1:]
	return opts, nil
}

func printError(status int, err error) int {
	fmt.Println(err)
	return status
}

func appendSpecifiedClasspath(classpaths []string, opts *options) []string {
	if opts.classpath == "" {
		return classpaths
	}
	return append(classpaths, opts.classpath)
}

func classpathExpression(opts *options) string {
	infos, err := ioutil.ReadDir(LIB_DIR)
	if err != nil {
		panic(err)
	}
	classpath := []string{}
	for _, info := range infos {
		if info.Mode().IsRegular() {
			classpath = append(classpath, filepath.Join(LIB_DIR, info.Name()))
		}
	}
	classpath = appendSpecifiedClasspath(classpath, opts)
	return strings.Join(classpath, ":")
}

func oneLiner(opts *options) *exec.Cmd {
	return exec.Command("groovy", "-classpath", classpathExpression(opts), "--basescript", "PochiBase", "-e", opts.expression)
}

func interactiveMode(opts *options) *exec.Cmd {
	return exec.Command("groovysh", "-classpath", classpathExpression(opts), "-e", "pochi = new jp.cafebabe.pochi.birthmarks.BirthmarkSystemHelper()")
}

func execScript(opts *options) *exec.Cmd {
	argv := []string{"-classpath", classpathExpression(opts), "--basescript", "PochiBase"}
	argv = append(argv, opts.args...)
	return exec.Command("groovy", argv...)
}

func constructCmdBuilder(opts *options) func(opts *options) *exec.Cmd {
	if opts.expression != "" {
		return oneLiner
	}
	if len(opts.args) == 0 {
		return interactiveMode
	}
	return execScript
}

func setConfigPath(configPath string) {
	os.Setenv(CONFIG_PATH, configPath)
}

func execute(builder func(opts *options) *exec.Cmd, opts *options) int {
	command := builder(opts)
	command.Stdin = os.Stdin
	command.Stdout = os.Stdout
	command.Stderr = os.Stderr
	// fmt.Printf("command: %s\n", strings.Join(command.Args, " "))
	if err := command.Start(); err != nil {
		fmt.Println(err.Error())
		return 3
	}
	if err := command.Wait(); err != nil {
		fmt.Println(err.Error())
		return 4
	}
	return 0
}

func perform(opts *options) int {
	if opts.helpFlag {
		return printError(0, fmt.Errorf(helpMessage(PROG_NAME)))
	}
	builder := constructCmdBuilder(opts)
	if opts.config != "" {
		setConfigPath(opts.config)
	}
	return execute(builder, opts)
}

func goMain(args []string) int {
	opts, err := parseArguments(args)
	if err != nil {
		return printError(1, err)
	}
	return perform(opts)
}

func main() {
	status := goMain(os.Args)
	os.Exit(status)
}
