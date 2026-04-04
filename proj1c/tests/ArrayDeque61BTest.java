import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

import deque.Deque61B;
import deque.ArrayDeque61B;
import deque.LinkedListDeque61B;

public class ArrayDeque61BTest {

    @Test
    public void testIterator() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(10);
        ad.addLast(20);
        ad.addLast(30);

        int sum = 0;
        for (int i : ad) {
            sum += i;
        }
        // 10 + 20 + 30 必须等于 60
        assertThat(sum).isEqualTo(60);
    }

    @Test
    public void testEquals() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        ad1.addLast("java");
        ad1.addLast("python");

        ad2.addLast("java");
        ad2.addLast("python");

        lld1.addLast("java");
        lld1.addLast("python");

        // 一样的主测逻辑，保证万无一失！
        assertThat(ad1).isEqualTo(ad2);
        assertThat(ad1).isEqualTo(lld1);

        // 空空的口袋和装了东西的口袋是不一样的哦！
        Deque61B<String> adEmpty = new ArrayDeque61B<>();
        assertThat(ad1).isNotEqualTo(adEmpty);
    }
}