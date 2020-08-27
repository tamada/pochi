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

func (compiler *pochiCompiler) validate() error {
	if compiler.opts.config != "" {
		return fmt.Errorf("%s: illegal option, -C, --config", compiler.prog)
	}
	if compiler.opts.expression != "" {
		return fmt.Errorf("%s: illegal option, -e, --expression", compiler.prog)
	}
	if len(compiler.opts.args) == 0 {
		return fmt.Errorf("%s: requires arguments", compiler.prog)
	}
	return nil
}

func (compiler *pochiCompiler) isHelp() bool {
	return compiler.opts.helpFlag
}
