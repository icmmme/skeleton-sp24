import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

import deque.Deque61B;
import deque.LinkedListDeque61B;
import deque.ArrayDeque61B;

public class LinkedListDeque61BTest {

    @Test
    public void testIterator() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");

        // 欸嘿，看看你的 for-each 循环能不能乖乖听话~
        int count = 0;
        for (String s : lld) {
            count++;
        }
        assertThat(count).isEqualTo(3);
    }

    @Test
    public void testEquals() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        lld1.addLast("apple");
        lld1.addLast("banana");

        lld2.addLast("apple");
        lld2.addLast("banana");

        ad1.addLast("apple");
        ad1.addLast("banana");

        // 两个内容一样的双胞胎，必须是相等的哦！
        assertThat(lld1).isEqualTo(lld2);

        // 就算是不同类型的妹妹（ArrayDeque），只要内容一样，也要一视同仁！
        assertThat(lld1).isEqualTo(ad1);

        // 如果别人多吃了一个橘子，那就不一样啦~
        lld2.addLast("orange");
        assertThat(lld1).isNotEqualTo(lld2);
    }
}