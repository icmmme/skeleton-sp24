package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    // 官方规定的37个按键
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        // 1. 创建一个能装37根琴弦的数组！
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];

        // 2. 用for循环，给每一根弦单独计算频率并实例化
        for (int i = 0; i < KEYBOARD.length(); i++) {
            double frequency = 440.0 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }

        // 3. 无限循环，开始监听键盘和发声
        while (true) {
            // 如果用户敲击了键盘
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                // 找出敲的这个键在 KEYBOARD 里的索引
                int index = KEYBOARD.indexOf(key);
                // 如果索引 >= 0，说明按的确实是合法的琴键，那就拨动它！
                if (index >= 0) {
                    strings[index].pluck();
                }
            }

            // 4. 把所有琴弦当前的波形高度（sample）全部加起来
            double sample = 0.0;
            for (GuitarString s : strings) {
                sample += s.sample();
            }

            // 5. 播放混合后的声音
            StdAudio.play(sample);

            // 6. 让所有琴弦的时间都往前推进一步（tic）
            for (GuitarString s : strings) {
                s.tic();
            }
        }
    }
}