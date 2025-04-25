-- 1. Вставляем артистов
INSERT INTO artists (name, bio, country) VALUES
('The Weeknd', 'Канадский певец и автор песен', 'Канада'),
('Dua Lipa', 'Британская певица и автор песен', 'Великобритания'),
('Imagine Dragons', 'Американская поп-рок группа', 'США'),
('Billie Eilish', 'Американская певица и автор песен', 'США'),
('BTS', 'Южнокорейский бойз-бэнд', 'Южная Корея');

-- 2. Вставляем альбомы (artist_id берем из предыдущей вставки)
INSERT INTO albums (title, artist_id, release_date, cover_url) VALUES
('After Hours', 1, '2020-03-20', '/covers/after_hours.jpg'),
('Future Nostalgia', 2, '2020-03-27', '/covers/future_nostalgia.jpg'),
('Evolve', 3, '2017-06-23', '/covers/evolve.jpg'),
('When We All Fall Asleep, Where Do We Go?', 4, '2019-03-29', '/covers/wwafawdwg.jpg'),
('BE', 5, '2020-11-20', '/covers/be.jpg');

-- 3. Вставляем треки с указанием album_id
INSERT INTO tracks (title, duration_sec, audio_url, artist_id, album_id, plays_count) VALUES
-- Альбом After Hours (id=1)
('Blinding Lights', 203, '/audio/weeknd_blinding_lights.mp3', 1, 1, 2500000),
('Save Your Tears', 215, '/audio/weeknd_save_your_tears.mp3', 1, 1, 1800000),

-- Альбом Future Nostalgia (id=2)
('Dont Start Now', 183, '/audio/dua_dont_start_now.mp3', 2, 2, 2200000),
('Levitating', 203, '/audio/dua_levitating.mp3', 2, 2, 1900000),

-- Альбом Evolve (id=3)
('Believer', 204, '/audio/imagine_believer.mp3', 3, 3, 3000000),
('Thunder', 187, '/audio/imagine_thunder.mp3', 3, 3, 2700000),

-- Альбом When We All Fall Asleep... (id=4)
('bad guy', 194, '/audio/billie_bad_guy.mp3', 4, 4, 3200000),
('everything i wanted', 244, '/audio/billie_everything.mp3', 4, 4, 1500000),

-- Альбом BE (id=5)
('Dynamite', 199, '/audio/bts_dynamite.mp3', 5, 5, 3500000),
('Butter', 164, '/audio/bts_butter.mp3', 5, 5, 2800000);
