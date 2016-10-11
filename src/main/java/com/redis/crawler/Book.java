package com.redis.crawler;

/**
 * Created by Administrator on 2016/10/10.
 */
import java.lang.reflect.Field;
import java.util.Arrays;
import static java.lang.System.out;

enum Tweedle { DEE, DUM }

public class Book {
    public long chapters = 0;
    public String[] characters = { "Alice", "White Rabbit" };
    public Tweedle twin = Tweedle.DEE;

    public void setChapters(long chapters) {
        this.chapters = chapters;
    }

    public void setCharacters(String[] characters) {
        this.characters = characters;
    }

    public void setTwin(Tweedle twin) {
        this.twin = twin;
    }

    public long getChapters() {
        return chapters;
    }

    public String[] getCharacters() {
        return characters;
    }

    public Tweedle getTwin() {
        return twin;
    }

    @Override
    public String toString() {
        return "Book{" +
                "chapters=" + chapters +
                ", characters=" + Arrays.toString(characters) +
                ", twin=" + twin +
                '}';
    }

    public static void main(String... args) {
        Book book = new Book();
        String fmt = "%6S:  %-12s = %s%n";

        try {
            Class<?> c = book.getClass();

            Field chap = c.getDeclaredField("chapters");
            out.format(fmt, "before", "chapters", book.chapters);
            chap.setLong(book, 12);
            out.format(fmt, "after", "chapters", chap.getLong(book));

            Field chars = c.getDeclaredField("characters");
            out.format(fmt, "before", "characters",
                    Arrays.asList(book.characters));
            String[] newChars = { "Queen", "King" };
            chars.set(book, newChars);
            out.format(fmt, "after", "characters",
                    Arrays.asList(book.characters));

            Field t = c.getDeclaredField("twin");
            out.format(fmt, "before", "twin", book.twin);
            t.set(book, Tweedle.DUM);
            out.format(fmt, "after", "twin", t.get(book));

            // production code should handle these exceptions more gracefully
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}
