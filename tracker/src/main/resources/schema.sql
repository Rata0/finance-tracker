-- Создание таблицы artists
CREATE TABLE artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    bio TEXT,
    country VARCHAR(100)
);

-- Создание таблицы albums
CREATE TABLE albums (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    artist_id BIGINT NOT NULL,
    release_date DATE,
    cover_url VARCHAR(255),
    CONSTRAINT fk_artist FOREIGN KEY (artist_id) REFERENCES artists(id)
);

-- Создание таблицы users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    username VARCHAR(30) NOT NULL,
    created_at DATE DEFAULT CURRENT_DATE
);

-- Создание таблицы tracks
CREATE TABLE tracks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    duration_sec INTEGER,
    audio_url VARCHAR(255),
    plays_count INTEGER DEFAULT 0,
    artist_id BIGINT,
    album_id BIGINT,
    CONSTRAINT fk_artist FOREIGN KEY (artist_id) REFERENCES artists(id),
    CONSTRAINT fk_album FOREIGN KEY (album_id) REFERENCES albums(id)
);

-- Создание таблицы playlists
CREATE TABLE playlists (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(50) NOT NULL,
    is_public BOOLEAN DEFAULT false,
    created_at DATE DEFAULT CURRENT_DATE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Создание таблицы playlist_tracks (составной первичный ключ)
CREATE TABLE playlist_tracks (
    playlist_id BIGINT NOT NULL,
    track_id BIGINT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (playlist_id, track_id),
    CONSTRAINT fk_playlist FOREIGN KEY (playlist_id) REFERENCES playlists(id),
    CONSTRAINT fk_track FOREIGN KEY (track_id) REFERENCES tracks(id)
);

-- Создание таблицы likes (составной первичный ключ)
CREATE TABLE likes (
    user_id BIGINT NOT NULL,
    track_id BIGINT NOT NULL,
    liked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, track_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_track FOREIGN KEY (track_id) REFERENCES tracks(id)
);

CREATE INDEX idx_track_artist ON tracks(artist_id);
CREATE INDEX idx_track_album ON tracks(album_id);
CREATE INDEX idx_playlist_user ON playlists(user_id);

CREATE OR REPLACE FUNCTION increment_play_count()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE tracks SET plays_count = plays_count + 1 WHERE id = NEW.track_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_play_increment
AFTER INSERT ON playlist_tracks
FOR EACH ROW EXECUTE FUNCTION increment_play_count();
