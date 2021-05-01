package exctractor;


import com.github.kiulian.downloader.model.formats.AudioFormat;
import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
import com.github.kiulian.downloader.model.quality.AudioQuality;
import com.github.kiulian.downloader.model.quality.VideoQuality;

import java.nio.file.Path;
import java.util.List;

public class args {
    public enum quality {
        BEST(0),
        GOOD(1),
        WORST(2);

        public int value;

        quality(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public AudioQuality toAudioQuality() {
            return switch (value) {
                case 1 -> AudioQuality.medium;
                case 2 -> AudioQuality.low;
                default -> AudioQuality.high;
            };
        }

        public VideoQuality toVideoQuality() {
            return switch (value) {
                case 1 -> VideoQuality.medium;
                case 2 -> VideoQuality.small;
                default -> VideoQuality.large;
            };
        }

        public AudioVideoFormat toAudioVideoFormat(List<AudioVideoFormat> audioVideoFormats) {
            return switch (value) {
                default -> audioVideoFormats.get(audioVideoFormats.size() - 1);
                case 1 -> audioVideoFormats.get(audioVideoFormats.size() / 2);
                case 2 -> audioVideoFormats.get(0);
            };
        }
    }

    private static class ARGS {
        public static final String ONLY_VIDEO = "-ov";
        public static final String ONLY_AUDIO = "-oa";
        public static final String QUALITY = "-q";
        private static final String PATH = "-p";
    }

    // region work with raw data

    private static boolean contains(String arg, String[] args) {
        for (String argument : args) {
            if ((argument).equals(arg))
                return true;
        }
        return false;
    }

    private static String extractArgValue(String arg, String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ((args[i]).equals(arg))
                return args[i + 1];
        }
        return null;
    }

    //endregion

    public final boolean OnlyVideo;
    public final boolean OnlyAudio;
    public final boolean VideoWithAudio;
    public final Path Path;
    public final quality Quality;

    private args(boolean onlyVideo, boolean onlyAudio, boolean videoWithAudio, quality quality, Path path) {
        this.OnlyVideo = onlyVideo;
        this.OnlyAudio = onlyAudio;
        this.VideoWithAudio = videoWithAudio;
        this.Quality = quality;
        this.Path = path;
    }


    public static args fromCall(String[] args) {
        boolean onlyVideo = false;
        boolean onlyAudio = false;
        boolean videoWithAudio = true;
        quality quality = exctractor.args.quality.BEST;
        Path path = java.nio.file.Path.of(System.getProperty("user.dir"));
        if (contains(ARGS.ONLY_AUDIO, args)) {
            videoWithAudio = false;
            onlyAudio = true;
        }
        if (contains(ARGS.ONLY_VIDEO, args)) {
            videoWithAudio = false;
            onlyAudio = false;
            onlyVideo = true;
        }
        if (contains(ARGS.QUALITY, args)) {
            quality = exctractor.args.quality.valueOf(extractArgValue(ARGS.QUALITY, args).toUpperCase());
        }
        if (contains(ARGS.PATH, args)) {
            path = java.nio.file.Path.of(extractArgValue(ARGS.PATH, args));
        }
        return new args(onlyVideo, onlyAudio, videoWithAudio, quality, path);
    }

    @Override
    public String toString() {
        return "args{" +
                "OnlyVideo=" + OnlyVideo +
                ", OnlyAudio=" + OnlyAudio +
                ", VideoWithAudio=" + VideoWithAudio +
                ", Path=" + Path +
                ", Quality=" + Quality +
                '}';
    }
}
