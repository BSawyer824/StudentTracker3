package com.wgu_android.studenttracker3.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.time.LocalDateTime;

/**********************************************************
 Terms Entity is the object class that will be used to hold all Term objects
 ********************************************************/

@Entity(tableName="terms_table")
public class TermsEntity {

    //*****************************************************************
    //Variable Declarations
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="term_id")
    private int termID;

    @NonNull
    @ColumnInfo(name="term_name")
    private String termName;

//    @ColumnInfo(name="term_start")
//    private LocalDateTime termStart;
//    @ColumnInfo(name="term_end")
//    private LocalDateTime termEnd;


    //*****************************************************************
    //Constructors
//    @Ignore //this constructor is ignored when the database automatically generates a term
//    public TermsEntity(int termID, String termName, LocalDateTime termStart, LocalDateTime termEnd) {
//        this.termID = termID;
//        this.termName = termName;
//        this.termStart = termStart;
//        this.termEnd = termEnd;
//    }
//
//    @Ignore //this constructor is ignored when the database automatically generates a term
//    public TermsEntity(int termID, String termName) {
//        this.termID = termID;
//        this.termName = termName;
//    }

    public TermsEntity(String termName) {
        this.termName = termName;
    }


    //*****************************************************************
    //Other Methods
    @Override
    public String toString() {
        return "TermsEntity{" +
                "term_id=" + termID +
                ", term_name='" + termName + '\'' +
                '}';
    }

    //*****************************************************************
    //Accessor/Getters
    public int getTermID() {
        return termID;
    }
    public String getTermName() {
        return termName;
    }
//    public LocalDateTime getTermStart() {
//        return termStart;
//    }
//    public LocalDateTime getTermEnd() {
//        return termEnd;
//    }


    //*****************************************************************
    //Mutators/Setters
    public void setTermID(int termID) {
        this.termID = termID;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

//    public void setTermStart(LocalDateTime termStart) {
//        this.termStart = termStart;
//    }
//
//    public void setTermEnd(LocalDateTime termEnd) {
//        this.termEnd = termEnd;
//    }


}
