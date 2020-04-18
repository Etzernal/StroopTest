package mk.ukim.finki.vvkn.stroopeffect.models;

import java.net.SocketImpl;

import mk.ukim.finki.vvkn.stroopeffect.fragments.SimulationFragment;

public class Result {
    // Primary key
    private Long id;

    private String gender;
    private String age;

    //Control Trial 1
    private double errorPercentageLettersNeutralWord;
    private long elapsedTimeLettersNeutralWord;
    private double errorPercentageLettersNeutralColor;
    private long elapsedTimeLettersNeutralColor;

    //Trial 1 Actual
    private double errorPercentageLettersCongruent;
    private long elapsedTimeLettersCongruent;
    private double errorPercentageLettersIncongruent;
    private long elapsedTimeLettersIncongruent;

    //Control Trial 2
    private double errorPercentageEmotionNeutralWord;
    private long elapsedTimeEmotionNeutralWord;
    private double errorPercentageEmotionNeutralColor;
    private long elapsedTimeEmotionNeutralColor;

    //Trial 2
    private double errorPercentageEmotionCongruent;
    private long elapsedTimeEmotionCongruent;
    private double errorPercentageEmotionIncongruent;
    private long elapsedTimeEmotionIncongruent;


    public Result() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {this.age = age;}

    // Set Error Percentage ------------------------------------------------------------------

    // Control Trial 1
    public void setErrorPercentageLettersNeutralWord(double errorPercentageLettersNeutralWord){
        this.errorPercentageLettersNeutralWord = errorPercentageLettersNeutralWord;
    }

    public void setErrorPercentageLettersNeutralColor(double errorPercentageLettersNeutralColor){
        this.errorPercentageLettersNeutralColor = errorPercentageLettersNeutralColor;
    }

    // Trial 1
    public void setErrorPercentageLettersCongruent(double errorPercentageLettersCongruent){
        this.errorPercentageLettersCongruent = errorPercentageLettersCongruent;
    }

    public void setErrorPercentageLettersIncongruent(double errorPercentageLettersIncongruent){
        this.errorPercentageLettersIncongruent = errorPercentageLettersIncongruent;
    }

    // Control Trial 2

    public void setErrorPercentageEmotionNeutralWord(double errorPercentageEmotionNeutralWord){
        this.errorPercentageEmotionNeutralWord = errorPercentageEmotionNeutralWord;
    }

    public void setErrorPercentageEmotionNeutralColor(double errorPercentageEmotionNeutralColor){
        this.errorPercentageEmotionNeutralColor = errorPercentageEmotionNeutralColor;
    }

    // Trial 2

    public void setErrorPercentageEmotionCongruent(double errorPercentageEmotionCongruent) {
        this.errorPercentageEmotionCongruent = errorPercentageEmotionCongruent;
    }

    public void setErrorPercentageEmotionIncongruent(double errorPercentageEmotionIncongruent) {
        this.errorPercentageEmotionIncongruent = errorPercentageEmotionIncongruent;
    }

    // ---------------------------------------------------------------------------------------

    // Set Elapsed Time ----------------------------------------------------------------------

    // Control Trial 1

    public void setElapsedTimeLettersNeutralWord(long elapsedTimeLettersNeutralWord) {
        this.elapsedTimeLettersNeutralWord = elapsedTimeLettersNeutralWord;
    }

    public void setElapsedTimeLettersNeutralColor(long elapsedTimeLettersNeutralColor) {
        this.elapsedTimeLettersNeutralColor = elapsedTimeLettersNeutralColor;
    }

    // Trial 1

    public void setElapsedTimeLettersCongruent(long elapsedTimeLettersCongruent) {
        this.elapsedTimeLettersCongruent = elapsedTimeLettersCongruent;
    }

    public void setElapsedTimeLettersIncongruent(long elapsedTimeLettersIncongruent) {
        this.elapsedTimeLettersIncongruent = elapsedTimeLettersIncongruent;
    }

    // Control Trial 2

    public void setElapsedTimeEmotionNeutralWord(long elapsedTimeEmotionNeutralWord) {
        this.elapsedTimeEmotionNeutralWord = elapsedTimeEmotionNeutralWord;
    }

    public void setElapsedTimeEmotionNeutralColor(long elapsedTimeEmotionNeutralColor) {
        this.elapsedTimeEmotionNeutralColor = elapsedTimeEmotionNeutralColor;
    }

    // Trial 2

    public void setElapsedTimeEmotionCongruent(long elapsedTimeEmotionCongruent) {
        this.elapsedTimeEmotionCongruent = elapsedTimeEmotionCongruent;
    }

    public void setElapsedTimeEmotionIncongruent(long elapsedTimeEmotionIncongruent) {
        this.elapsedTimeEmotionIncongruent = elapsedTimeEmotionIncongruent;
    }



    public Long getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() { return age; }

    public double getErrorPercentageLettersNeutralWord(){ return errorPercentageLettersNeutralWord; }
    public double getErrorPercentageLettersNeutralColor(){ return errorPercentageLettersNeutralColor; }
    public double getErrorPercentageLettersCongruent(){ return errorPercentageLettersCongruent; }
    public double getErrorPercentageLettersIncongruent(){ return errorPercentageLettersIncongruent; }
    public double getErrorPercentageEmotionNeutralWord(){ return errorPercentageEmotionNeutralWord; }
    public double getErrorPercentageEmotionNeutralColor(){ return errorPercentageEmotionNeutralColor; }
    public double getErrorPercentageEmotionCongruent(){ return errorPercentageEmotionCongruent; }
    public double getErrorPercentageEmotionIncongruent(){ return errorPercentageEmotionIncongruent; }

    public long getElapsedTimeLettersNeutralWord(){ return elapsedTimeLettersNeutralWord; }
    public long getElapsedTimeLettersNeutralColor(){ return elapsedTimeLettersNeutralColor; }
    public long getElapsedTimeLettersCongruent(){ return elapsedTimeLettersCongruent; }
    public long getElapsedTimeLettersIncongruent(){return elapsedTimeLettersIncongruent; }
    public long getElapsedTimeEmotionNeutralWord(){ return elapsedTimeEmotionNeutralWord; }
    public long getElapsedTimeEmotionNeutralColor(){ return elapsedTimeEmotionNeutralColor; }
    public long getElapsedTimeEmotionCongruent(){ return elapsedTimeEmotionCongruent; }
    public long getElapsedTimeEmotionIncongruent(){ return elapsedTimeEmotionIncongruent; }


    public void setElapsedTime(int type, long milliseconds)
    {
        if (type == SimulationFragment.LETTERSNEUTRALWORD){ setElapsedTimeLettersNeutralWord(milliseconds);}
        else if (type == SimulationFragment.LETTERSNEUTRALCOLOR){ setElapsedTimeLettersNeutralColor(milliseconds);}
        else if (type == SimulationFragment.LETTERSCONGRUENT){ setElapsedTimeLettersCongruent(milliseconds);}
        else if (type == SimulationFragment.LETTERSINCONGRUENT){ setElapsedTimeLettersIncongruent(milliseconds);}
        else if (type == SimulationFragment.EMOTIONNEUTRALWORD){ setElapsedTimeEmotionNeutralWord(milliseconds);}
        else if (type == SimulationFragment.EMOTIONNEUTRALCOLOR){ setElapsedTimeEmotionNeutralColor(milliseconds);}
        else if (type == SimulationFragment.EMOTIONCONGRUENT){ setElapsedTimeEmotionCongruent(milliseconds);}
        else { setElapsedTimeEmotionIncongruent(milliseconds);}
    }

    public void setErrorPercentage(int type, double error)
    {
        if (type == SimulationFragment.LETTERSNEUTRALWORD){ setErrorPercentageLettersNeutralWord(error);}
        else if (type == SimulationFragment.LETTERSNEUTRALCOLOR){ setErrorPercentageLettersNeutralColor(error);}
        else if (type == SimulationFragment.LETTERSCONGRUENT){ setErrorPercentageLettersCongruent(error);}
        else if (type == SimulationFragment.LETTERSINCONGRUENT){ setErrorPercentageLettersIncongruent(error);}
        else if (type == SimulationFragment.EMOTIONNEUTRALWORD){ setErrorPercentageEmotionNeutralWord(error);}
        else if (type == SimulationFragment.EMOTIONNEUTRALCOLOR){ setErrorPercentageEmotionNeutralColor(error);}
        else if (type == SimulationFragment.EMOTIONCONGRUENT){ setErrorPercentageEmotionCongruent(error);}
        else { setErrorPercentageEmotionIncongruent(error);}
    }

    @Override
    public String toString() {
        return String.format("%s\n%.2f %d\n%.2f %d", getGender(),
                getErrorPercentageLettersNeutralWord(),getElapsedTimeLettersNeutralWord(),
                getErrorPercentageLettersNeutralColor(),getElapsedTimeLettersNeutralColor(),
                getErrorPercentageLettersCongruent(), getElapsedTimeLettersCongruent(),
                getErrorPercentageLettersIncongruent(),getElapsedTimeLettersIncongruent(),
                getErrorPercentageEmotionNeutralWord(),getElapsedTimeEmotionNeutralWord(),
                getErrorPercentageEmotionNeutralColor(),getElapsedTimeEmotionNeutralColor(),
                getErrorPercentageEmotionCongruent(),getElapsedTimeEmotionCongruent(),
                getErrorPercentageEmotionIncongruent(),getElapsedTimeEmotionIncongruent());
    }
}
