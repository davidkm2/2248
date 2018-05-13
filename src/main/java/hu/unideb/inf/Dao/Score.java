package hu.unideb.inf.Dao;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The {@code Score} class implements the score.
 *
 * @author John David Balogh
 */
@XmlRootElement
public class Score {
    private SimpleStringProperty score;
    private SimpleStringProperty date;

    /**
     * Creates an empty instance of Score.
     */
    public Score() {
        this.score = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
    }

    /**
     * Creates a new instance of Score with a specified score and date.
     *
     * @param score The achieved score.
     * @param date The actual date.
     */
    public Score(String score, String date) {
        this.score = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.score.set(score);
        this.date.set(date);
    }

    /**
     * Get the score.
     *
     * @return Score.
     */
    public String getScore() {
        return score.get();
    }

    /**
     * Set the score.
     *
     * @param score Score.
     */
    @XmlElement
    public void setScore(String score) {
        this.score.set(score);
    }

    /**
     * Get the date.
     *
     * @return Date.
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Set the date.
     *
     * @param date Date.
     */
    @XmlElement
    public void setDate(String date) {
        this.date.set(date);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", date=" + date +
                '}';
    }
}
