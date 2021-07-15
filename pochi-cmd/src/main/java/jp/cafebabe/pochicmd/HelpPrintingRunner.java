package jp.cafebabe.pochicmd;

public class HelpPrintingRunner implements Runner {
    private Main main;

    public HelpPrintingRunner(Main main) {
        this.main = main;
    }

    @Override
    public final void run(Arguments args) {
        System.out.println(helpMessage());
    }

    @Override
    public Mode mode() {
        return Mode.HelpMode;
    }

    public static final String helpMessage() {
        return String.format("pochi version %s%n" +
                        "pochi [OPTIONS] [SCRIPT FILE [ARGV...]]%n" +
                        "OPTIONS%n" +
                        "    -c, --classpath <CLASSPATH>      specifies the classpaths for Groovy (JVM) separated with colon (:).%n" +
                        "    -C, --config <CONFIG_FILE>       specifies the configuration file.%n" +
                        "    -e, --expression <EXPRESSION>    specifies one line script.%n" +
                        "    -w, --working-dir <DIR>          specifies the working directory.%n" +
                        "    -v, --verbose                    sets as verbose mode.%n%n" +
                        "    -h, --help                       prints this message.%n" +
                        "SCRIPT_FILE [ARGV...]%n" +
                        "    Groovy script file and its arguments.%n" +
                        "    If no script files and no expression were given, pochi runs on interactive mode.",
                Main.VERSION);
    }
}
