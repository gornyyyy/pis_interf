package lab2;

import com.beust.jcommander.Parameter;

public class CliArgs {
    @Parameter(names = {"-f", "--file"}, description = "Путь к файлу", required = true)
    public String filePath;

    @Parameter(names = {"-o", "--oper"}, description = "Операция: print или count", required = true)
    public String operation;

    @Parameter(names = {"-h", "--help"}, help = true, description = "Показать справку")
    public boolean help;
}
