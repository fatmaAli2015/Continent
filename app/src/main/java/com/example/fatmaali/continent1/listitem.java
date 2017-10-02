package com.example.fatmaali.continent1;

/**
 * Created by Fatma Ali on 19/09/2017.
 */

public class listitem {
    private String Title;
    private String file_id;

    public listitem(String title, String file_id) {
        Title = title;
        this.file_id = file_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }
}
