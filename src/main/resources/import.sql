CREATE OR REPLACE FUNCTION audit_trigger()
RETURNS TRIGGER AS $$
BEGIN
  IF TG_OP = 'INSERT' THEN
    NEW.data_inserimento := COALESCE(NEW.data_inserimento, now());
    NEW.data_update := now();
    NEW.user_inserimento := COALESCE(NEW.user_inserimento, current_user);
    NEW.user_update := current_user;
  ELSIF TG_OP = 'UPDATE' THEN
    NEW.data_update := now();
    NEW.user_update := current_user;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    note TEXT,
    data_inserimento TIMESTAMP NOT NULL,
    data_update TIMESTAMP NOT NULL,
    user_inserimento VARCHAR(50) NOT NULL,
    user_update VARCHAR(50) NOT NULL
);

CREATE TRIGGER trg_audit_users
BEFORE INSERT OR UPDATE ON users
FOR EACH ROW EXECUTE FUNCTION audit_trigger();

CREATE TABLE preferences (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    desired_gender VARCHAR(50),
    max_distance INTEGER,
    min_age INTEGER,
    max_age INTEGER,
    note TEXT,
    data_inserimento TIMESTAMP NOT NULL,
    data_update TIMESTAMP NOT NULL,
    user_inserimento VARCHAR(50) NOT NULL,
    user_update VARCHAR(50) NOT NULL,
    CONSTRAINT fk_preferences_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TRIGGER trg_audit_preferences
BEFORE INSERT OR UPDATE ON preferences
FOR EACH ROW EXECUTE FUNCTION audit_trigger();

CREATE TABLE interests (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    note TEXT,
    data_inserimento TIMESTAMP NOT NULL,
    data_update TIMESTAMP NOT NULL,
    user_inserimento VARCHAR(50) NOT NULL,
    user_update VARCHAR(50) NOT NULL
);

CREATE TRIGGER trg_audit_interests
BEFORE INSERT OR UPDATE ON interests
FOR EACH ROW EXECUTE FUNCTION audit_trigger();

CREATE TABLE user_interests (
    user_id INTEGER NOT NULL,
    interest_id INTEGER NOT NULL,
    note TEXT,
    data_inserimento TIMESTAMP NOT NULL,
    data_update TIMESTAMP NOT NULL,
    user_inserimento VARCHAR(50) NOT NULL,
    user_update VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, interest_id),
    CONSTRAINT fk_user_interests_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_interests_interest FOREIGN KEY (interest_id) REFERENCES interests(id) ON DELETE CASCADE
);

CREATE TRIGGER trg_audit_user_interests
BEFORE INSERT OR UPDATE ON user_interests
FOR EACH ROW EXECUTE FUNCTION audit_trigger();

CREATE TABLE conversations (
    id SERIAL PRIMARY KEY,
    user1_id INTEGER NOT NULL,
    user2_id INTEGER NOT NULL,
    note TEXT,
    data_inserimento TIMESTAMP NOT NULL,
    data_update TIMESTAMP NOT NULL,
    user_inserimento VARCHAR(50) NOT NULL,
    user_update VARCHAR(50) NOT NULL,
    CONSTRAINT fk_conversations_user1 FOREIGN KEY (user1_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_conversations_user2 FOREIGN KEY (user2_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TRIGGER trg_audit_conversations
BEFORE INSERT OR UPDATE ON conversations
FOR EACH ROW EXECUTE FUNCTION audit_trigger();


CREATE TABLE messages (
    id SERIAL PRIMARY KEY,
    conversation_id INTEGER NOT NULL,
    sender_id INTEGER NOT NULL,
    message_text TEXT NOT NULL,
    sent_at TIMESTAMP NOT NULL,
    note TEXT,
    data_inserimento TIMESTAMP NOT NULL,
    data_update TIMESTAMP NOT NULL,
    user_inserimento VARCHAR(50) NOT NULL,
    user_update VARCHAR(50) NOT NULL,
    CONSTRAINT fk_messages_conversation FOREIGN KEY (conversation_id) REFERENCES conversations(id) ON DELETE CASCADE,
    CONSTRAINT fk_messages_sender FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TRIGGER trg_audit_messages
BEFORE INSERT OR UPDATE ON messages
FOR EACH ROW EXECUTE FUNCTION audit_trigger();


CREATE TABLE subscriptions (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    subscription_type VARCHAR(50),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    note TEXT,
    data_inserimento TIMESTAMP NOT NULL,
    data_update TIMESTAMP NOT NULL,
    user_inserimento VARCHAR(50) NOT NULL,
    user_update VARCHAR(50) NOT NULL,
    CONSTRAINT fk_subscriptions_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TRIGGER trg_audit_subscriptions
BEFORE INSERT OR UPDATE ON subscriptions
FOR EACH ROW EXECUTE FUNCTION audit_trigger();


CREATE TABLE settings (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    language VARCHAR(10),
    theme VARCHAR(50),
    push_notifications_enabled BOOLEAN DEFAULT true,
    note TEXT,
    data_inserimento TIMESTAMP NOT NULL,
    data_update TIMESTAMP NOT NULL,
    user_inserimento VARCHAR(50) NOT NULL,
    user_update VARCHAR(50) NOT NULL,
    CONSTRAINT fk_settings_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TRIGGER trg_audit_settings
BEFORE INSERT OR UPDATE ON settings
FOR EACH ROW EXECUTE FUNCTION audit_trigger();


INSERT INTO users (email, password, first_name, last_name, note)
VALUES ('john.doe@example.com', 'hashed_password', 'John', 'Doe', 'Utente di esempio');

INSERT INTO preferences (user_id, desired_gender, max_distance, min_age, max_age, note)
VALUES (1, 'Female', 50, 18, 35, 'Preferisce partner attive e dinamiche');

INSERT INTO interests (name, description, note)
VALUES ('Hiking', 'Attività alaria aperta, ideale per chi ama la natura', 'Interesse popolare');

INSERT INTO user_interests (user_id, interest_id, note)
VALUES (1, 1, 'Utente appassionato di trekking e passeggiate');

INSERT INTO conversations (user1_id, user2_id, note)
VALUES (1, 2, 'Chat tra John Doe e Jane Smith');

INSERT INTO messages (conversation_id, sender_id, message_text, sent_at, note)
VALUES (1, 1, 'Ciao, come va?', now(), 'Primo messaggio di saluto');

INSERT INTO subscriptions (user_id, subscription_type, start_date, end_date, note)
VALUES (1, 'Premium', now(), now() + interval '30 days', 'Abbonamento Premium attivato');

INSERT INTO settings (user_id, language, theme, push_notifications_enabled, note)
VALUES (1, 'en', 'dark', true, 'Preferenze impostate: tema scuro e lingua inglese');

