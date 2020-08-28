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
	LIB_DIR     = "lib"
)
const VERSION = "2.0.0"

type pochiRunner struct {
	prog string
	opts *options
}

type pochiCompiler struct {
	prog string
	opts *options
}

type pochi interface {
	execute() int
	validate() error
	isHelp() bool
}

type options struct {
	classpath   string
	config      string
	dest        string
	expression  string
	helpFlag    bool
	verboseMode bool
	args        []string
}

func (opts *options) printIfVerbose(message string) {
	if opts.verboseMode {
		fmt.Println(message)
	}
}

func helpMessageOfCompiler(prog string) string {
	return fmt.Sprintf(`pochi version %s
%s [OPTIONS] <SCRIPT_FILEs...>
OPTIONS
    -c, --classpath <CLASSPATH>      specifies classpaths for Groovy (JVM) searated with colon (:).
    -d, --dest <DIR>                 specifies the destination directory.

    -h, --help                       prints this message.
SCRIPT_FILE
    Groovy script file name for compiling.`, VERSION, prog)
}

func helpMessage(prog string) string {
	if prog == "pochic" {
		return helpMessageOfCompiler(prog)
	}
	return fmt.Sprintf(`pochi version %s
%s [OPTIONS] [SCRIPT_FILE [ARGV...]]
OPTIONS
    -c, --classpath <CLASSPATH>      specifies classpaths for Groovy (JVM) searated with colon (:).
    -C, --config <CONFIG_FILE>       specifies configuration file.
    -e, --expression <EXPRESSION>    specifies command line script.

    -h, --help                       prints this message.
SCRIPT_FILE [ARGV...]
    Groovy script file name and its arguments.
    If no script files and no expression were given, pochi runs on interactive mode.`, VERSION, prog)
}

func ExistsDir(path string) bool {
	info, err := os.Stat(path)
	return err == nil && info.IsDir()
}

func pochiHome() string {
	generators := []func() string{
		func() string { return os.Getenv(HOME_NAME) },
		func() string { return "/opt/pochi" },
		func() string { return BREW_PATH },
		func() string { return "distribution/target" },
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
	flags.BoolVarP(&opts.helpFlag, "help", "h", false, "prints this message")
	flags.StringVarP(&opts.classpath, "classpath", "c", "", "specifies classpaths for Groovy (JVM) separated with colon (:)")
	flags.StringVarP(&opts.config, "config", "C", "", "specifies command line script")
	flags.StringVarP(&opts.dest, "dest", "d", "", "specifies the destination directory")
	flags.BoolVarP(&opts.verboseMode, "verbose", "v", false, "verbose mode")
	flags.StringVarP(&opts.expression, "expression", "e", "", "specifies configuration file")
	return flags, opts
}

func parseArguments(args []string) (pochi, error) {
	flags, opts := buildFlagSet()
	if err := flags.Parse(args); err != nil {
		return nil, err
	}
	newArgs := flags.Args()
	opts.args = newArgs[1:]
	progName := filepath.Base(args[0])
	if progName == "pochic" {
		return &pochiCompiler{prog: progName, opts: opts}, nil
	}
	return &pochiRunner{prog: progName, opts: opts}, nil
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
	libDir := filepath.Join(pochiHome(), LIB_DIR)
	infos, err := ioutil.ReadDir(libDir)
	if err != nil {
		panic(err)
	}
	classpath := []string{}
	for _, info := range infos {
		if info.Mode().IsRegular() {
			classpath = append(classpath, filepath.Join(libDir, info.Name()))
		}
	}
	classpath = appendSpecifiedClasspath(classpath, opts)
	return strings.Join(classpath, ":")
}

func execCommand(command *exec.Cmd, opts *options) int {
	command.Stdin = os.Stdin
	command.Stdout = os.Stdout
	command.Stderr = os.Stderr
	opts.printIfVerbose(fmt.Sprintf("command: %s", strings.Join(command.Args, " ")))
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

func goMain(args []string) int {
	pochi, err := parseArguments(args)
	if err != nil {
		return printError(1, err)
	}
	if err := pochi.validate(); err != nil {
		return printError(2, err)
	}
	if pochi.isHelp() {
		return printError(0, fmt.Errorf(helpMessage(filepath.Base(args[0]))))
	}
	return pochi.execute()
}

func main() {
	status := goMain(os.Args)
	os.Exit(status)
}
