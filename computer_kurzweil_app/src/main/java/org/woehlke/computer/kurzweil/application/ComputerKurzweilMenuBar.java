package org.woehlke.computer.kurzweil.application;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.event.KeyEvent;

@Log4j2
@Getter
public class ComputerKurzweilMenuBar extends JMenuBar {

    private final ComputerKurzweilContext ctx;

    public ComputerKurzweilMenuBar(ComputerKurzweilContext ctx) {
        this.ctx = ctx;
        ComputerKurzweilProperties p = this.ctx.getProperties();
        cyclicCellularAutomatonMenuItemLabel = p.getCca().getView().getTitle();
        randomWalkMenuItemLabel = p.getRandomwalk().getView().getTitle();
        diffusionLimitedAggregationMenuItemLabel = p.getDla().getView().getTitle();
        mandelbrotMenuItemLabel = p.getMandelbrot().getView().getTitle();
        simulatedEvolutionMenuItemLabel = p.getSimulatedevolution().getView().getTitle();
        cyclicCellularAutomatonTooltip = p.getCca().getView().getSubtitle();
        randomWalkTooltip = p.getRandomwalk().getView().getSubtitle();
        diffusionLimitedAggregationTooltip = p.getDla().getView().getSubtitle();
        mandelbrotTooltip = p.getMandelbrot().getView().getSubtitle();
        simulatedEvolutionTooltip = p.getSimulatedevolution().getView().getSubtitle();
        fileMenu = new JMenu(fileMenuLabel);
        exitMenuItem = new JMenuItem(exitCmd, null);
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setToolTipText(exitTooltip);
        exitMenuItem.addActionListener((event) -> System.exit(0));
        fileMenu.add(exitMenuItem);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        startMenu = new JMenu(startMenuLabel);
        cyclicCellularAutomatonMenuItem = new JMenuItem(startCmd+cyclicCellularAutomatonMenuItemLabel, null);
        randomWalkMenuItem = new JMenuItem(startCmd+randomWalkMenuItemLabel, null);
        diffusionLimitedAggregationMenuItem = new JMenuItem(startCmd+diffusionLimitedAggregationMenuItemLabel, null);
        mandelbrotMenuItem = new JMenuItem(startCmd+mandelbrotMenuItemLabel, null);
        simulatedEvolutionMenuItem = new JMenuItem(startCmd+simulatedEvolutionMenuItemLabel, null);
        cyclicCellularAutomatonMenuItem.setMnemonic(KeyEvent.VK_1);
        cyclicCellularAutomatonMenuItem.setToolTipText(cyclicCellularAutomatonTooltip);
        cyclicCellularAutomatonMenuItem.addActionListener((event) -> cyclicCellularAutomatonStart());
        randomWalkMenuItem.setMnemonic(KeyEvent.VK_2);
        randomWalkMenuItem.setToolTipText(randomWalkTooltip);
        randomWalkMenuItem.addActionListener((event) -> randomWalkStart());
        diffusionLimitedAggregationMenuItem.setMnemonic(KeyEvent.VK_3);
        diffusionLimitedAggregationMenuItem.setToolTipText(diffusionLimitedAggregationTooltip);
        diffusionLimitedAggregationMenuItem.addActionListener((event) -> diffusionLimitedAggregationStart());
        mandelbrotMenuItem.setMnemonic(KeyEvent.VK_4);
        mandelbrotMenuItem.setToolTipText(mandelbrotTooltip);
        mandelbrotMenuItem.addActionListener((event) -> mandelbrotStart());
        simulatedEvolutionMenuItem.setMnemonic(KeyEvent.VK_5);
        simulatedEvolutionMenuItem.setToolTipText(simulatedEvolutionTooltip);
        simulatedEvolutionMenuItem.addActionListener((event) -> simulatedEvolutionStart());
        startMenu.add(cyclicCellularAutomatonMenuItem);
        startMenu.add(randomWalkMenuItem);
        startMenu.add(diffusionLimitedAggregationMenuItem);
        startMenu.add(mandelbrotMenuItem);
        startMenu.add(simulatedEvolutionMenuItem);
        startMenu.setMnemonic(KeyEvent.VK_S);
        this.add(fileMenu);
        this.add(startMenu);
    }


    private void cyclicCellularAutomatonStart(){

    }

    private void randomWalkStart(){

    }

    private void diffusionLimitedAggregationStart(){

    }

    private void mandelbrotStart(){

    }

    private void simulatedEvolutionStart(){

    }

    private final JMenu fileMenu;
    private final JMenu startMenu;

    private final String cyclicCellularAutomatonMenuItemLabel;
    private final String randomWalkMenuItemLabel;
    private final String diffusionLimitedAggregationMenuItemLabel;
    private final String mandelbrotMenuItemLabel;
    private final String simulatedEvolutionMenuItemLabel;

    private final String cyclicCellularAutomatonTooltip;
    private final String randomWalkTooltip;
    private final String diffusionLimitedAggregationTooltip;
    private final String mandelbrotTooltip;
    private final String simulatedEvolutionTooltip;

    private final JMenuItem cyclicCellularAutomatonMenuItem;
    private final JMenuItem randomWalkMenuItem;
    private final JMenuItem diffusionLimitedAggregationMenuItem;
    private final JMenuItem mandelbrotMenuItem;
    private final JMenuItem simulatedEvolutionMenuItem;

    private final String startCmd = "Start ";
    private final String fileMenuLabel = "File";
    private final String startMenuLabel = "Start";

    private final String exitCmd = "Exit";
    private final String exitTooltip = "Exit application";

    private final JMenuItem exitMenuItem;
}
