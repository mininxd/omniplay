package com.joaomgcd.taskerm.event.media;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.alarmclock.ClockContract;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "mt")
public final class OutputMusicTrackChanged {
    private final String album;
    private final String allMetadata;
    private final String[] allMetadataKeys;
    private final String appPackage;
    private final String art;
    private final String artist;
    private final Long duration;
    private final String durationFormatted;
    private final String genre;
    private final Boolean isPlaying;
    private final Integer numberTracks;
    private final String[] queueIcons;
    private final String[] queueTitles;
    private final Integer rating;
    private final String state;
    private final String track;
    private final Integer trackNumber;
    private final Long year;

    public OutputMusicTrackChanged(String str, String str2, String str3, Long l, String str4, Long l2, String str5, Boolean bool, String str6, String str7, String str8, Integer num, Integer num2, Integer num3, String[] strArr, String[] strArr2, String str9, String[] strArr3) {
        this.track = str;
        this.artist = str2;
        this.album = str3;
        this.year = l;
        this.art = str4;
        this.duration = l2;
        this.durationFormatted = str5;
        this.isPlaying = bool;
        this.appPackage = str6;
        this.state = str7;
        this.genre = str8;
        this.trackNumber = num;
        this.numberTracks = num2;
        this.rating = num3;
        this.queueTitles = strArr;
        this.queueIcons = strArr2;
        this.allMetadata = str9;
        this.allMetadataKeys = strArr3;
    }

    @TaskerOutputVariable(labelResId = R.string.music_track_track, name = "track")
    public final String getTrack() {
        return this.track;
    }

    @TaskerOutputVariable(labelResId = R.string.music_track_artist, name = "artist")
    public final String getArtist() {
        return this.artist;
    }

    @TaskerOutputVariable(labelResId = R.string.music_track_album, name = "album")
    public final String getAlbum() {
        return this.album;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.music_track_year_description, labelResId = R.string.music_track_year, name = ClockContract.InstancesColumns.YEAR)
    public final Long getYear() {
        return this.year;
    }

    @TaskerOutputVariable(labelResId = R.string.music_track_art, name = "art")
    public final String getArt() {
        return this.art;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.music_track_duration_description, labelResId = R.string.music_track_duration, name = "duration")
    public final Long getDuration() {
        return this.duration;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.music_track_duration_description, labelResId = R.string.music_track_duration_formatted, name = "duration_formatted")
    public final String getDurationFormatted() {
        return this.durationFormatted;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.music_track_playing_description, labelResId = R.string.music_track_playing, name = "playing")
    public final Boolean isPlaying() {
        return this.isPlaying;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.music_track_app_description, labelResId = R.string.pl_app, name = "app")
    public final String getAppPackage() {
        return this.appPackage;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.music_track_state_description, labelResId = R.string.word_state, name = "state")
    public final String getState() {
        return this.state;
    }

    @TaskerOutputVariable(labelResId = R.string.music_track_genre, name = "genre")
    public final String getGenre() {
        return this.genre;
    }

    @TaskerOutputVariable(labelResId = R.string.music_track_number, name = "track_number")
    public final Integer getTrackNumber() {
        return this.trackNumber;
    }

    @TaskerOutputVariable(labelResId = R.string.music_track_number_tracks, name = "number_tracks")
    public final Integer getNumberTracks() {
        return this.numberTracks;
    }

    @TaskerOutputVariable(labelResId = R.string.music_track_rating, name = "rating")
    public final Integer getRating() {
        return this.rating;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.music_track_queue_titles_description, labelResId = R.string.music_track_queue_titles, name = "queue_titles")
    public final String[] getQueueTitles() {
        return this.queueTitles;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.music_track_queue_icons_description, labelResId = R.string.music_track_queue_icons, name = "queue_icons")
    public final String[] getQueueIcons() {
        return this.queueIcons;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.all_metadata_music_track_explained, labelResId = R.string.all_metadata, name = "all_metadata")
    public final String getAllMetadata() {
        return this.allMetadata;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.all_metadata_keys_explained, labelResId = R.string.all_metadata, name = "all_metadata_keys")
    public final String[] getAllMetadataKeys() {
        return this.allMetadataKeys;
    }
}
