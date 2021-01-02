__pochi() {
    local cur prev words cword split
    _init_completion -n : || return

    case "${prev}" in
        --expression | -e)
            return 0
            ;;
        --classpath | -c)
            compopt -o filenames -o nospace
            COMPREPLY=($(compgen -d -- "$cur"))
            return 0
            ;;
        --working-dir | -w)
            compopt -o filenames
            COMRREPLY=($(compgen -d -- "$cur"))
            return 0
            ;;
        --config | -C)
            compopt -o filenames
            COMRREPLY=($(compgen -d -- "$cur"))
            return 0
            ;;
    esac
    opts=" -C -c -e -v -w -h --classpath --config --expression --help --working-dir --verbose"
    if [[ "$cur" =~ ^\- ]]; then
        COMPREPLY=( $(compgen -W "${opts}" -- "${cur}") )
        return 0
    else
        compopt -o filenames
        COMPREPLY=($(compgen -d -- "$cur"))
    fi
}

complete -F __pochi -o bashdefault -o default pochi
