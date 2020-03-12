package org.woehlke.computer.kurzweil.commons.widgets;

import lombok.Getter;
import lombok.ToString;
import org.woehlke.computer.kurzweil.commons.has.HasTabTitle;

import javax.swing.*;
import java.awt.event.KeyEvent;

@Getter
@ToString
public abstract class SubTabImpl extends JPanel implements HasTabTitle {

    private final String title;
    private final String subTitle;
    private final String toolTipText;
    private final Icon icon;
    private final int keyEvent;

    public SubTabImpl(String title, String subTitle, String toolTipText, Icon icon, int keyEvent) {
        this.title = title;
        this.subTitle = subTitle;
        this.toolTipText = toolTipText;
        this.icon = icon;
        this.keyEvent = keyEvent;
    }

    public SubTabImpl(String title) {
        this.title = title;
        this.subTitle = title;
        this.toolTipText = title;
        this.icon = null;
        this.keyEvent = KeyEvent.CHAR_UNDEFINED;
    }
}
