package mk.ukim.finki.vvkn.stroopeffect.models;

import java.net.SocketImpl;
import java.util.stream.DoubleStream;

import mk.ukim.finki.vvkn.stroopeffect.MainActivity;
import mk.ukim.finki.vvkn.stroopeffect.fragments.SimulationFragment;

public class Result {
    // Primary key
    private Long id;

    private String gender;
    private String age;

    // Warped
    private double errorPercentageWarpedPracNeutral;
    private long elapsedTimeWarpedPracNetural;
    private double errorPercentageWarpedPracMixed;
    private long elapsedTimeWarpedPracMixed;
    private double errorPercentageWarpedCongruent;
    private long elapsedTimeWarpedCongruent;
    private double errorPercentageWarpedIncongruent;
    private long elapsedTimeWarpedIncongruent;
    private double errorPercentageWarpedMixed;
    private long elapsedTimeWarpedMixed;

    // Emotion
    private double errorPercentageEmotionPracNeutral;
    private long elapsedTimeEmotionPracNeutral;
    private double errorPercentageEmotionPracMixed;
    private long elapsedTimeEmotionPracMixed;
    private double errorPercentageEmotionCongruent;
    private long elapsedTimeEmotionCongruent;
    private double errorPercentageEmotionIncongruent;
    private long elapsedTimeEmotionIncongruent;
    private double errorPercentageEmotionMixed;
    private long elapsedTimeEmotionMixed;


    public Result() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {this.age = age;}

    // Set Error Percentage ------------------------------------------------------------------

    // Warped
    public void setErrorPercentageWarpedPracNeutral(double errorPercentageLettersNeutralWord){
        this.errorPercentageWarpedPracNeutral = errorPercentageLettersNeutralWord;
    }
    public void setErrorPercentageWarpedPracMixed(double errorPercentageLettersNeutralColor){
        this.errorPercentageWarpedPracMixed = errorPercentageLettersNeutralColor;
    }
    public void setErrorPercentageWarpedCongruent(double errorPercentageLettersCongruent){
        this.errorPercentageWarpedCongruent = errorPercentageLettersCongruent;
    }
    public void setErrorPercentageWarpedIncongruent(double errorPercentageLettersIncongruent){
        this.errorPercentageWarpedIncongruent = errorPercentageLettersIncongruent;
    }
    public void setErrorPercentageWarpedMixed(double errorPercentageLettersIncongruent){
        this.errorPercentageWarpedMixed = errorPercentageLettersIncongruent;
    }

    // Emotion
    public void setErrorPercentageEmotionPracNeutral(double errorPercentageEmotionNeutralWord){
        this.errorPercentageEmotionPracNeutral = errorPercentageEmotionNeutralWord;
    }
    public void setErrorPercentageEmotionPracMixed(double errorPercentageEmotionNeutralColor){
        this.errorPercentageEmotionPracMixed = errorPercentageEmotionNeutralColor;
    }
    public void setErrorPercentageEmotionCongruent(double errorPercentageEmotionCongruent) {
        this.errorPercentageEmotionCongruent = errorPercentageEmotionCongruent;
    }
    public void setErrorPercentageEmotionIncongruent(double errorPercentageEmotionIncongruent) {
        this.errorPercentageEmotionIncongruent = errorPercentageEmotionIncongruent;
    }
    public void setErrorPercentageEmotionMixed(double errorPercentageEmotionIncongruent) {
        this.errorPercentageEmotionMixed = errorPercentageEmotionIncongruent;
    }

    // ---------------------------------------------------------------------------------------

    // Set Elapsed Time ----------------------------------------------------------------------

    // Warped
    public void setElapsedTimeWarpedPracNeutral(long elapsedTimeLettersNeutralWord) {
        this.elapsedTimeWarpedPracNetural = elapsedTimeLettersNeutralWord;
    }
    public void setElapsedTimeWarpedPracMixed(long elapsedTimeLettersNeutralColor) {
        this.elapsedTimeWarpedPracMixed = elapsedTimeLettersNeutralColor;
    }
    public void setElapsedTimeWarpedCongruent(long elapsedTimeLettersCongruent) {
        this.elapsedTimeWarpedCongruent = elapsedTimeLettersCongruent;
    }
    public void setElapsedTimeWarpedIncongruent(long elapsedTimeLettersIncongruent) {
        this.elapsedTimeWarpedIncongruent = elapsedTimeLettersIncongruent;
    }
    public void setElapsedTimeWarpedMixed(long elapsedTimeLettersIncongruent) {
        this.elapsedTimeWarpedMixed = elapsedTimeLettersIncongruent;
    }
    // Emotion
    public void setElapsedTimeEmotionPracNeutral(long elapsedTimeEmotionNeutralWord) {
        this.elapsedTimeEmotionPracNeutral = elapsedTimeEmotionNeutralWord;
    }
    public void setElapsedTimeEmotionPracMixed(long elapsedTimeEmotionNeutralColor) {
        this.elapsedTimeEmotionPracMixed = elapsedTimeEmotionNeutralColor;
    }
    public void setElapsedTimeEmotionCongruent(long elapsedTimeEmotionCongruent) {
        this.elapsedTimeEmotionCongruent = elapsedTimeEmotionCongruent;
    }
    public void setElapsedTimeEmotionIncongruent(long elapsedTimeEmotionIncongruent) {
        this.elapsedTimeEmotionIncongruent = elapsedTimeEmotionIncongruent;
    }
    public void setElapsedTimeEmotionMixed(long elapsedTimeEmotionMixed) {
        this.elapsedTimeEmotionMixed= elapsedTimeEmotionMixed;
    }

    public Long getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() { return age; }

    public double getErrorPercentageWarpedPracNeutral(){ return errorPercentageWarpedPracNeutral; }
    public double getErrorPercentageWarpedPracMixed(){ return errorPercentageWarpedPracMixed; }
    public double getErrorPercentageWarpedCongruent(){ return errorPercentageWarpedCongruent; }
    public double getErrorPercentageWarpedIncongruent(){ return errorPercentageWarpedIncongruent; }
    public double getErrorPercentageWarpedMixed(){ return errorPercentageWarpedMixed; }
    public double getErrorPercentageEmotionPracNeutral(){ return errorPercentageEmotionPracNeutral; }
    public double getErrorPercentageEmotionPracMixed(){ return errorPercentageEmotionPracMixed; }
    public double getErrorPercentageEmotionCongruent(){ return errorPercentageEmotionCongruent; }
    public double getErrorPercentageEmotionIncongruent(){ return errorPercentageEmotionIncongruent; }
    public double getErrorPercentageEmotionMixed(){ return errorPercentageEmotionMixed; }



    public long getElapsedTimeWarpedPracNetural(){ return elapsedTimeWarpedPracNetural; }
    public long getElapsedTimeWarpedPracMixed(){ return elapsedTimeWarpedPracMixed; }
    public long getElapsedTimeWarpedCongruent(){ return elapsedTimeWarpedCongruent; }
    public long getElapsedTimeWarpedIncongruent(){return elapsedTimeWarpedIncongruent; }
    public long getElapsedTimeWarpedMixed(){return elapsedTimeWarpedMixed; }
    public long getElapsedTimeEmotionPracNeutral(){ return elapsedTimeEmotionPracNeutral; }
    public long getElapsedTimeEmotionPracMixed(){ return elapsedTimeEmotionPracMixed; }
    public long getElapsedTimeEmotionCongruent(){ return elapsedTimeEmotionCongruent; }
    public long getElapsedTimeEmotionIncongruent(){ return elapsedTimeEmotionIncongruent; }
    public long getElapsedTimeEmotionMixed(){ return elapsedTimeEmotionMixed; }


    public void setElapsedTime(String mode, long milliseconds)
    {
        // 0,0,0 ; 0,0,3 ; 0,1,1 ; 0,1,2 ; 0,1,3 ;
        // 1,0,0 ; 1,0,3 ; 1,1,1 ; 1,1,2 ; 1,1,3
        // 1st column - warped or emotions
        // 2nd column - prac or actual
        // 3rd column - neutral, congruent, incongruent or mixed

        System.out.println("I AM HERE TOO ");
        if (mode.equals("000")) {setElapsedTimeWarpedPracNeutral(milliseconds);}
        else if (mode.equals("003")) {setElapsedTimeWarpedPracMixed(milliseconds);}
        else if (mode.equals("011")) {setElapsedTimeWarpedCongruent(milliseconds);}
        else if (mode.equals("012")) {setElapsedTimeWarpedIncongruent(milliseconds);}
        else if (mode.equals("013")) {setElapsedTimeWarpedMixed(milliseconds);}
        else if (mode.equals("100")) {setElapsedTimeEmotionPracNeutral(milliseconds);}
        else if (mode.equals("103")) {setElapsedTimeEmotionPracMixed(milliseconds);}
        else if (mode.equals("111")) {setElapsedTimeEmotionCongruent(milliseconds);}
        else if (mode.equals("112")) {setElapsedTimeEmotionIncongruent(milliseconds);}
        else if (mode.equals("113")) {setElapsedTimeEmotionMixed(milliseconds);}
    }

    public void setErrorPercentage(String mode, double error)
    {
        System.out.println("I AM HERE");
        if (mode.equals("000")) {setErrorPercentageWarpedPracNeutral(error);}
        else if (mode.equals("003")) {setErrorPercentageWarpedPracMixed(error);}
        else if (mode.equals("011")) {setErrorPercentageWarpedCongruent(error);}
        else if (mode.equals("012")) {setErrorPercentageWarpedIncongruent(error);}
        else if (mode.equals("013")) {setErrorPercentageWarpedMixed(error);}
        else if (mode.equals("100")) {setErrorPercentageEmotionPracNeutral(error);}
        else if (mode.equals("103")) {setErrorPercentageEmotionPracMixed(error);}
        else if (mode.equals("111")) {setErrorPercentageEmotionCongruent(error);}
        else if (mode.equals("112")) {setErrorPercentageEmotionIncongruent(error);}
        else if (mode.equals("113")) {setErrorPercentageEmotionMixed(error);}
    }

    @Override
    public String toString() {
        return String.format("%s\n%.2f %d\n%.2f %d", getGender(),
                getErrorPercentageWarpedPracNeutral(),getElapsedTimeWarpedPracNetural(),
                getErrorPercentageWarpedPracMixed(),getElapsedTimeWarpedPracMixed(),
                getErrorPercentageWarpedCongruent(), getElapsedTimeWarpedCongruent(),
                getErrorPercentageWarpedIncongruent(),getElapsedTimeWarpedIncongruent(),
                getErrorPercentageWarpedMixed(),getElapsedTimeWarpedMixed(),
                getErrorPercentageEmotionPracNeutral(),getElapsedTimeEmotionPracNeutral(),
                getErrorPercentageEmotionPracMixed(),getElapsedTimeEmotionPracMixed(),
                getErrorPercentageEmotionCongruent(),getElapsedTimeEmotionCongruent(),
                getErrorPercentageEmotionIncongruent(),getElapsedTimeEmotionIncongruent(),
                getErrorPercentageEmotionMixed(),getElapsedTimeEmotionMixed());
    }
}
