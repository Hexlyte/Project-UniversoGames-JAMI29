package org.generation.italy.universogames.model;

public class News extends Entity {

    private String title;
    private String imgCover;
    private String preview;
    private String text;
    private String pubDate;
    private String pubTime;
    private String author;
    
    
    

    public News(int id, String title, String imgCover, String preview, String text, String pubDate, String pubTime,
			String author) {
		super(id);
		this.title = title;
		this.imgCover = imgCover;
		this.preview = preview;
		this.text = text;
		this.pubDate = pubDate;
		this.pubTime = pubTime;
		this.author = author;
	}

    public News() {
        super();
    }

    public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getPubTime() {
		return pubTime;
	}


	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}


	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


	@Override
	public String toString() {
		return "{title:" + title + ", imgCover:" + imgCover + ", text:" + text + ", pubDate:" + pubDate + ", pubTime:"
				+ pubTime + ", author:" + author + ", toString():" + super.toString() + "}\n";
	}


}