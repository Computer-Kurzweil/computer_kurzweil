package org.woehlke.computer.kurzweil.tabs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.swing.*;

@Log
@Getter
@ToString
@EqualsAndHashCode
public abstract class TabPanel extends JPanel implements Tab {

    private static final long serialVersionUID = 7526471155622776147L;
}
