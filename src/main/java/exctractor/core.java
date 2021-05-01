package exctractor;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioFormat;
import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
import com.github.kiulian.downloader.model.formats.VideoFormat;
import com.github.kiulian.downloader.model.quality.AudioQuality;


public class core {


    public static void DownloadVideo(String link, args args) {
        try {
            YoutubeDownloader youtubeDownloader = new YoutubeDownloader();
            YoutubeVideo YoutubeVideo = youtubeDownloader.getVideo(parser.getVideoId(link));
            if (args.OnlyAudio) {
                AudioFormat audio;
                try {
                    audio = YoutubeVideo.findAudioWithQuality(AudioQuality.high).get(0);
                } catch (java.lang.IndexOutOfBoundsException e) {
                    System.out.println("Couldn't find suitable audio quality, downloading that we own");
                    audio = YoutubeVideo.audioFormats().get(0);
                }
                System.out.println("Only audio stream was successfully found, downloading...");
                YoutubeVideo.download(audio, args.Path.toFile());
            } else if (args.OnlyVideo) {
                VideoFormat video;
                try {
                    video = YoutubeVideo.findVideoWithQuality(args.Quality.toVideoQuality()).get(0);
                } catch (java.lang.IndexOutOfBoundsException e) {
                    System.out.println("Couldn't find suitable video quality, downloading that we own");
                    video = YoutubeVideo.videoFormats().get(0);
                }
                System.out.println("Only video stream was successfully found, downloading...");
                YoutubeVideo.download(video, args.Path.toFile());
            } else if (args.VideoWithAudio) {
                AudioVideoFormat audioVideo = args.Quality.toAudioVideoFormat(YoutubeVideo.videoWithAudioFormats());
                System.out.println("Stream was successfully found, downloading...");
                YoutubeVideo.download(audioVideo, args.Path.toFile());
            }
            System.out.printf("Done! You're welcome at %s%n", args.Path.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
