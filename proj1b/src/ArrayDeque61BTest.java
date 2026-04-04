import java.util.List;
import java.util.Objects;

/**
 * ArrayDeque61B 完整测试 - 单文件版
 * 每个功能独立测试，包含边界情况
 */
public class ArrayDeque61BTest {

    // ==================== 测试工具 ====================

    private static int testsPassed = 0;
    private static int testsFailed = 0;
    private static String currentTest = "";

    private static void startTest(String name) {
        currentTest = name;
        System.out.println("\n========== " + name + " ==========");
    }

    private static void assertEq(Object expected, Object actual, String desc) {
        boolean pass = (Objects.equals(expected, actual));
        if (pass) {
            System.out.println("  ✓ " + desc);
            testsPassed++;
        } else {
            System.out.println("  ✗ " + desc);
            System.out.println("    Expected: " + expected);
            System.out.println("    Actual:   " + actual);
            testsFailed++;
        }
    }

    private static void assertTrue(boolean condition, String desc) {
        if (condition) {
            System.out.println("  ✓ " + desc);
            testsPassed++;
        } else {
            System.out.println("  ✗ " + desc + " (expected true, got false)");
            testsFailed++;
        }
    }

    private static void assertNull(Object actual, String desc) {
        if (actual == null) {
            System.out.println("  ✓ " + desc);
            testsPassed++;
        } else {
            System.out.println("  ✗ " + desc);
            System.out.println("    Expected: null");
            System.out.println("    Actual:   " + actual);
            testsFailed++;
        }
    }

    // ==================== 1. addFirst 测试 ====================

    private static void testAddFirstBasic() {
        startTest("addFirst - 基础功能");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addFirst(5);
        assertEq(1, deque.size(), "添加1个后size=1");
        assertEq(List.of(5), deque.toList(), "列表为[5]");
        assertEq(5, deque.get(0), "get(0)=5");
    }

    private static void testAddFirstMultiple() {
        startTest("addFirst - 多个元素");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        assertEq(3, deque.size(), "size=3");
        assertEq(List.of("c", "b", "a"), deque.toList(), "顺序为[c,b,a]（每次加前面）");
        assertEq("c", deque.get(0), "get(0)=c");
        assertEq("b", deque.get(1), "get(1)=b");
        assertEq("a", deque.get(2), "get(2)=a");
    }

    private static void testAddFirstEmpty() {
        startTest("addFirst - 空队列添加");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        assertTrue(deque.isEmpty(), "初始为空");
        deque.addFirst(100);
        assertFalse(!deque.isEmpty(), "添加后非空");
        assertEq(100, deque.get(0), "能正确获取");
    }

    // ==================== 2. addLast 测试 ====================

    private static void testAddLastBasic() {
        startTest("addLast - 基础功能");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(10);
        assertEq(1, deque.size(), "添加1个后size=1");
        assertEq(List.of(10), deque.toList(), "列表为[10]");
    }

    private static void testAddLastMultiple() {
        startTest("addLast - 多个元素");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("x");
        deque.addLast("y");
        deque.addLast("z");

        assertEq(3, deque.size(), "size=3");
        assertEq(List.of("x", "y", "z"), deque.toList(), "顺序为[x,y,z]");
        assertEq("x", deque.get(0), "get(0)=x");
        assertEq("z", deque.get(2), "get(2)=z");
    }

    // ==================== 3. addFirst + addLast 混合测试 ====================

    private static void testMixedAdd() {
        startTest("addFirst + addLast 混合");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(2);    // [2]
        deque.addFirst(1);   // [1, 2]
        deque.addLast(3);    // [1, 2, 3]
        deque.addFirst(0);   // [0, 1, 2, 3]

        assertEq(4, deque.size(), "size=4");
        assertEq(List.of(0, 1, 2, 3), deque.toList(), "最终顺序[0,1,2,3]");
        assertEq(0, deque.get(0), "get(0)=0");
        assertEq(3, deque.get(3), "get(3)=3");
    }

    private static void testMixedAddAlternating() {
        startTest("addFirst + addLast 交替");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        // 交替添加：F(前), L(后), F, L, F, L
        deque.addFirst("F1");  // [F1]
        deque.addLast("L1");   // [F1, L1]
        deque.addFirst("F2");  // [F2, F1, L1]
        deque.addLast("L2");   // [F2, F1, L1, L2]
        deque.addFirst("F3");  // [F3, F2, F1, L1, L2]
        deque.addLast("L3");   // [F3, F2, F1, L1, L2, L3]

        assertEq(6, deque.size(), "size=6");
        assertEq("F3", deque.get(0), "第一个是F3");
        assertEq("L3", deque.get(5), "最后一个是L3");
        assertEq(List.of("F3", "F2", "F1", "L1", "L2", "L3"), deque.toList(), "完整顺序");
    }

    // ==================== 4. removeFirst 测试 ====================

    private static void testRemoveFirstEmpty() {
        startTest("removeFirst - 空队列");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        assertNull(deque.removeFirst(), "空队列返回null");
        assertEq(0, deque.size(), "size保持0");
    }

    private static void testRemoveFirstSingle() {
        startTest("removeFirst - 单个元素");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("only");
        assertEq("only", deque.removeFirst(), "移除并返回only");
        assertEq(0, deque.size(), "size变为0");
        assertTrue(deque.isEmpty(), "变为空");
        assertEq(List.of(), deque.toList(), "列表为空");
    }

    private static void testRemoveFirstMultiple() {
        startTest("removeFirst - 多个元素");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);  // [1, 2, 3]

        assertEq(1, deque.removeFirst(), "移除1");
        assertEq(List.of(2, 3), deque.toList(), "剩余[2,3]");

        assertEq(2, deque.removeFirst(), "移除2");
        assertEq(List.of(3), deque.toList(), "剩余[3]");

        assertEq(3, deque.removeFirst(), "移除3");
        assertTrue(deque.isEmpty(), "为空");

        assertNull(deque.removeFirst(), "再移除返回null");
    }

    private static void testRemoveFirstAfterAddFirst() {
        startTest("removeFirst - 先加前面再移除");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");  // [c, b, a]

        assertEq("c", deque.removeFirst(), "移除c（最先加的）");
        assertEq("b", deque.removeFirst(), "移除b");
        assertEq("a", deque.removeFirst(), "移除a");
    }

    // ==================== 5. removeLast 测试 ====================

    private static void testRemoveLastEmpty() {
        startTest("removeLast - 空队列");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        assertNull(deque.removeLast(), "空队列返回null");
    }

    private static void testRemoveLastSingle() {
        startTest("removeLast - 单个元素");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addFirst("solo");
        assertEq("solo", deque.removeLast(), "移除并返回solo");
        assertTrue(deque.isEmpty(), "变为空");
    }

    private static void testRemoveLastMultiple() {
        startTest("removeLast - 多个元素");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);  // [10, 20, 30]

        assertEq(30, deque.removeLast(), "移除30（最后加的）");
        assertEq(20, deque.removeLast(), "移除20");
        assertEq(10, deque.removeLast(), "移除10");
        assertNull(deque.removeLast(), "再移除返回null");
    }

    private static void testRemoveLastAfterAddFirst() {
        startTest("removeLast - 先加前面再从后面移除");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addFirst("x");
        deque.addFirst("y");
        deque.addFirst("z");  // [z, y, x]

        assertEq("x", deque.removeLast(), "移除x（最后面）");
        assertEq("y", deque.removeLast(), "移除y");
        assertEq("z", deque.removeLast(), "移除z");
    }

    // ==================== 6. removeFirst + removeLast 混合测试 ====================

    private static void testMixedRemove() {
        startTest("removeFirst + removeLast 混合");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);  // [1, 2, 3, 4]

        assertEq(1, deque.removeFirst(), "removeFirst -> 1");  // [2, 3, 4]
        assertEq(4, deque.removeLast(), "removeLast -> 4");    // [2, 3]
        assertEq(2, deque.removeFirst(), "removeFirst -> 2");  // [3]
        assertEq(3, deque.removeLast(), "removeLast -> 3");    // []

        assertTrue(deque.isEmpty(), "最终为空");
    }

    // ==================== 7. isEmpty 测试 ====================

    private static void testIsEmptyBasic() {
        startTest("isEmpty - 基础");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        assertTrue(deque.isEmpty(), "新队列为空");

        deque.addFirst(1);
        assertFalse(deque.isEmpty(), "添加后非空");

        deque.removeFirst();
        assertTrue(deque.isEmpty(), "移除后为空");
    }

    private static void testIsEmptyAfterOperations() {
        startTest("isEmpty - 各种操作后");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        // 添加再移除
        deque.addLast("a");
        deque.removeLast();
        assertTrue(deque.isEmpty(), "addLast+removeLast后为空");

        // 多次添加再全部移除
        deque.addFirst("x");
        deque.addLast("y");
        deque.addFirst("z");
        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();
        assertTrue(deque.isEmpty(), "多次操作后为空");
    }

    // ==================== 8. size 测试 ====================

    private static void testSizeBasic() {
        startTest("size - 基础");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        assertEq(0, deque.size(), "初始size=0");

        deque.addFirst(1);
        assertEq(1, deque.size(), "添加后size=1");

        deque.addLast(2);
        deque.addFirst(3);
        assertEq(3, deque.size(), "再添加后size=3");
    }

    private static void testSizeAfterRemove() {
        startTest("size - 移除后");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        assertEq(3, deque.size(), "3个元素");

        deque.removeFirst();
        assertEq(2, deque.size(), "移除1个后size=2");

        deque.removeLast();
        assertEq(1, deque.size(), "再移除后size=1");

        deque.removeFirst();
        assertEq(0, deque.size(), "全部移除后size=0");
    }

    // ==================== 9. toList 测试 ====================

    private static void testToListEmpty() {
        startTest("toList - 空队列");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        assertEq(List.of(), deque.toList(), "空列表");
    }

    private static void testToListOrder() {
        startTest("toList - 顺序正确性");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("a");
        deque.addLast("b");
        deque.addFirst("c");
        // 实际顺序应该是 [c, a, b]

        List<String> result = deque.toList();
        assertEq("c", result.get(0), "第0个是c");
        assertEq("a", result.get(1), "第1个是a");
        assertEq("b", result.get(2), "第2个是b");
        assertEq(List.of("c", "a", "b"), result, "完整列表匹配");
    }

    private static void testToListImmutability() {
        startTest("toList - 不改变原队列");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(1);
        deque.addLast(2);

        List<Integer> list = deque.toList();
        list.add(999);  // 修改返回的列表

        assertEq(2, deque.size(), "原队列size不变");
        assertEq(List.of(1, 2), deque.toList(), "原队列内容不变");
    }

    // ==================== 10. get 测试 ====================

    private static void testGetBasic() {
        startTest("get - 基础获取");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        assertEq("a", deque.get(0), "get(0)=a");
        assertEq("b", deque.get(1), "get(1)=b");
        assertEq("c", deque.get(2), "get(2)=c");
    }

    private static void testGetOutOfBounds() {
        startTest("get - 越界情况");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(100);

        assertNull(deque.get(-1), "负数索引返回null");
        assertNull(deque.get(1), "等于size返回null");
        assertNull(deque.get(100), "大于size返回null");
    }

    private static void testGetEmpty() {
        startTest("get - 空队列");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        assertNull(deque.get(0), "空队列get(0)返回null");
    }

    // ==================== 11. getRecursive 测试 ====================

    private static void testGetRecursiveBasic() {
        startTest("getRecursive - 基础获取");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        assertEq(10, deque.getRecursive(0), "getRecursive(0)=10");
        assertEq(20, deque.getRecursive(1), "getRecursive(1)=20");
        assertEq(30, deque.getRecursive(2), "getRecursive(2)=30");
    }

    private static void testGetRecursiveOutOfBounds() {
        startTest("getRecursive - 越界");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("x");

        assertNull(deque.getRecursive(-1), "负数返回null");
        assertNull(deque.getRecursive(5), "越界返回null");
    }

    // ==================== 12. 扩容测试 ====================

    private static void testResizeExpand() {
        startTest("扩容 - 超过初始容量");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        // 初始容量为8，添加10个元素触发扩容
        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }

        assertEq(10, deque.size(), "size=10");
        assertEq(0, deque.get(0), "第0个正确");
        assertEq(9, deque.get(9), "第9个正确");
        assertEq(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), deque.toList(), "全部顺序正确");
    }

    private static void testResizeExpandAddFirst() {
        startTest("扩容 - 从前面添加触发");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 10; i++) {
            deque.addFirst("item" + i);
        }

        assertEq(10, deque.size(), "size=10");
        assertEq("item9", deque.get(0), "最先添加的在最后");
    }

    // ==================== 13. 缩容测试 ====================

    private static void testResizeShrink() {
        startTest("缩容 - 使用率低于25%");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        // 先添加20个（容量会扩到至少20）
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        // 移除17个，剩下3个（3 < 容量/4时触发缩容）
        for (int i = 0; i < 17; i++) {
            deque.removeFirst();
        }

        assertEq(3, deque.size(), "剩余3个");
        assertEq(List.of(17, 18, 19), deque.toList(), "内容正确[17,18,19]");
        assertEq(17, deque.get(0), "get(0)=17");
        assertEq(19, deque.get(2), "get(2)=19");
    }

    // ==================== 14. 循环数组边界测试 ====================

    private static void testCircularAddFirst() {
        startTest("循环数组 - 从0往前加");
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        // 假设初始First=0，往前加应该绕到数组末尾
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        assertEq(3, deque.size(), "size=3");
        assertEq("c", deque.get(0), "顺序正确c在最前");
        assertEq("a", deque.get(2), "a在最后");
    }

    private static void testCircularMixed() {
        startTest("循环数组 - 前后交替填满");
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        // 先填满右边
        for (int i = 0; i < 5; i++) {
            deque.addLast(i);  // [0,1,2,3,4]
        }

        // 再往左边加（测试绕回）
        deque.addFirst(100);
        deque.addFirst(200);  // [200,100,0,1,2,3,4]

        assertEq(7, deque.size(), "size=7");
        assertEq(200, deque.get(0), "200在最前");
        assertEq(4, deque.get(6), "4在最后");
    }

    // ==================== 主方法 ====================

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     ArrayDeque61B 完整功能测试       ║");
        System.out.println("╚══════════════════════════════════════╝");

        // addFirst 测试
        testAddFirstBasic();
        testAddFirstMultiple();
        testAddFirstEmpty();

        // addLast 测试
        testAddLastBasic();
        testAddLastMultiple();

        // 混合添加测试
        testMixedAdd();
        testMixedAddAlternating();

        // removeFirst 测试
        testRemoveFirstEmpty();
        testRemoveFirstSingle();
        testRemoveFirstMultiple();
        testRemoveFirstAfterAddFirst();

        // removeLast 测试
        testRemoveLastEmpty();
        testRemoveLastSingle();
        testRemoveLastMultiple();
        testRemoveLastAfterAddFirst();

        // 混合移除测试
        testMixedRemove();

        // isEmpty 测试
        testIsEmptyBasic();
        testIsEmptyAfterOperations();

        // size 测试
        testSizeBasic();
        testSizeAfterRemove();

        // toList 测试
        testToListEmpty();
        testToListOrder();
        testToListImmutability();

        // get 测试
        testGetBasic();
        testGetOutOfBounds();
        testGetEmpty();

        // getRecursive 测试
        testGetRecursiveBasic();
        testGetRecursiveOutOfBounds();

        // 扩容测试
        testResizeExpand();
        testResizeExpandAddFirst();

        // 缩容测试
        testResizeShrink();

        // 循环数组测试
        testCircularAddFirst();
        testCircularMixed();

        // 结果汇总
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║           测试结果汇总               ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  通过: " + String.format("%-25d", testsPassed) + "║");
        System.out.println("║  失败: " + String.format("%-25d", testsFailed) + "║");
        System.out.println("║  总计: " + String.format("%-25d", (testsPassed + testsFailed)) + "║");
        System.out.println("╚══════════════════════════════════════╝");

        if (testsFailed == 0) {
            System.out.println("🎉 所有测试通过！");
        } else {
            System.out.println("❌ 有 " + testsFailed + " 个测试失败");
            System.exit(1);
        }
    }
}