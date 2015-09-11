public class Message
{
    public static Message POISON_PILL = new Message("poison_pill");
    private String content;

    public Message(String content)
    {
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
}
