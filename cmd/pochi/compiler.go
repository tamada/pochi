package main

import (
	"fmt"
	"os/exec"
)

func appendDestIfNeeded(argv []string, compiler *pochiCompiler) []string {
	if compiler.opts.dest != "" {
		return append(argv, fmt.Sprintf("-d=%s", compiler.opts.dest))
	}
	return argv
}

func (compiler *pochiCompiler) execute() int {
	argv := []string{"-classpath", classpathExpression(compiler.opts), "--basescript", "PochiBase"}
	argv = appendDestIfNeeded(argv, compiler)
	argv = append(argv, compiler.opts.args...)
	return execCommand(exec.Command("groovyc", argv...), compiler.opts)
}

func (compiler *pochiCompiler) validate() (int, error) {
	if compiler.opts.config != "" {
		return 2, fmt.Errorf("%s: illegal option, -C, --config", compiler.prog)
	}
	if compiler.opts.expression != "" {
		return 4, fmt.Errorf("%s: illegal option, -e, --expression", compiler.prog)
	}
	if len(compiler.opts.args) == 0 {
		return 8, fmt.Errorf("%s: requires arguments", compiler.prog)
	}
	return 0, compiler.errorHelp()
}

func (compiler *pochiCompiler) errorHelp() error {
	if compiler.opts.helpFlag {
		return fmt.Errorf(helpMessageOfCompiler(compiler.prog))
	}
	return nil
}
