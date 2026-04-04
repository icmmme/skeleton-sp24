package gh2;

import edu.princeton.cs.algs4.StdAudio;

public class AutoGuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        // 1. 初始化 37 根琴弦 (和之前一样)
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];
        for (int i = 0; i < KEYBOARD.length(); i++) {
            double frequency = 440.0 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }

        // 2. 准备你的乐谱！(这里是一首超简单的《小星星》开头)
        // 字母代表琴键，空格代表休止符或者长音
        String song = "qqeettr eewwqq2 ";

        // 3. 定义每个音符持续的时间 (通过 tic 的次数来控制)
        // 44100 次是一秒，我们让每个音符响大概 0.4 秒
        int ticsPerNote = (int) (44100 * 0.4);

        // 4. 开始自动演奏！
        System.out.println("学姐专属点唱机，开始播放！");

        for (int i = 0; i < song.length(); i++) {
            char note = song.charAt(i);

            // 如果不是空格，就去拨动对应的琴弦
            if (note != ' ') {
                int index = KEYBOARD.indexOf(note);
                if (index >= 0) {
                    strings[index].pluck();
                }
            }

            // 关键来了！让时间飞逝，播放这个音符的持续时间
            for (int t = 0; t < ticsPerNote; t++) {
                double sample = 0.0;
                for (GuitarString s : strings) {
                    sample += s.sample();
                }

                StdAudio.play(sample);

                for (GuitarString s : strings) {
                    s.tic();
                }
            }
        }
    }
}