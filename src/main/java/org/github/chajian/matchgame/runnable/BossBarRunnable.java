package org.github.chajian.matchgame.runnable;

import org.bukkit.scheduler.BukkitRunnable;
import org.github.chajian.matchgame.bar.NoteBar;

import java.util.ArrayList;
import java.util.List;

/**
 * BossBar线程
 * @author Chajian
 */
public class BossBarRunnable extends BukkitRunnable {

    List<NoteBar> noteBars = new ArrayList<>();

    @Override
    public void run() {
        for(int i = 0 ; i < noteBars.size() ; i++){
            NoteBar noteBar = noteBars.get(i);
            if (noteBar.decrease())
                noteBar.destory();
        }
    }


    public void addNoteBar(NoteBar noteBar){
        noteBars.add(noteBar);
    }
}
