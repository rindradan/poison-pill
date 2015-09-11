package mg.rindra.poisonpill.model;

public class Message
{
    public static Message POISON_PILL = new Message("poison_pill");
    private String content;
    private int index = 0;

    public Message(String content)
    {
        this.content = content;
    }

    public Message(int index, String content)
    {
        this.index = index;
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }
}
