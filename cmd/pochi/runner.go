package main

import (
	"fmt"
	"os"
	"os/exec"
	"path/filepath"
)

func oneLiner(opts *options) *exec.Cmd {
	argv := []string{"-classpath", classpathExpression(opts), "--basescript", "PochiBase", "-e", opts.expression}
	if opts.verboseMode {
		argv = append(argv, "--debug")
	}
	return exec.Command("groovy", argv...)
}

func interactiveMode(opts *options) *exec.Cmd {
	argv := []string{"-classpath", classpathExpression(opts), "-e", "pochi = new jp.cafebabe.pochi.BirthmarkSystemHelper()"}
	if opts.verboseMode {
		argv = append(argv, "--debug")
	}
	return exec.Command("groovysh", argv...)
}

func execScript(opts *options) *exec.Cmd {
	argv := []string{"-classpath", classpathExpression(opts), "--basescript", "PochiBase"}
	if opts.verboseMode {
		argv = append(argv, "--debug")
	}
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

func (runner *pochiRunner) validate() (int, error) {
	if runner.opts.dest != "" {
		return 2, fmt.Errorf("%s: wrong option -- -d, --dest", runner.prog)
	}
	return 0, runner.errorHelp()
}

func (runner *pochiRunner) errorHelp() error {
	if runner.opts.helpFlag {
		return fmt.Errorf(helpMessage(filepath.Base(runner.prog)))
	}
	return nil
}

func (runner *pochiRunner) execute() int {
	if runner.opts.config != "" {
		setConfigPath(runner.opts.config)
	}
	builder := constructCmdBuilder(runner.opts)
	return execCommand(builder(runner.opts), runner.opts)
}
