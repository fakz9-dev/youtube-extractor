import exctractor.core;

import static exctractor.args.fromCall;

public class main {
    public static void main(String[] args) {
        core.DownloadVideo(args[0], fromCall(args));
    }
}
