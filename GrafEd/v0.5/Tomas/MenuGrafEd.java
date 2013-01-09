import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.KeyEvent;

public class MenuGrafEd {

  public MenuGrafEd(GrafEd okno) {
    MenuBar myMenuBar = new MenuBar();
    okno.setMenuBar( myMenuBar );
    Menu myFileMenu = new Menu( "Soubor" );
    Menu myHelpMenu = new Menu( "Nápověda" );
    myMenuBar.add( myFileMenu );
    myMenuBar.add( myHelpMenu );
    MenuItem myFileOpenMenuItem = new MenuItem( "Otevřít", new MenuShortcut( KeyEvent.VK_O )  );
    MenuItem myFileSaveMenuItem = new MenuItem( "Uložit jako", new MenuShortcut( KeyEvent.VK_S )  );
    MenuItem myFileExitMenuItem = new MenuItem( "Ukončit", new MenuShortcut( KeyEvent.VK_X ) );
    MenuItem myHelpAboutMenuItem = new MenuItem( "O Aplikaci" );
    myFileOpenMenuItem.addActionListener(okno);
    myFileSaveMenuItem.addActionListener(okno);
    myFileExitMenuItem.addActionListener(okno);
    myHelpAboutMenuItem.addActionListener(okno);
    myFileOpenMenuItem.setActionCommand( "open" );
    myFileSaveMenuItem.setActionCommand("save");
    myFileExitMenuItem.setActionCommand( "exit" );
    myHelpAboutMenuItem.setActionCommand( "about" );
    myFileMenu.add(myFileOpenMenuItem);
    myFileMenu.add(myFileSaveMenuItem);
    myFileMenu.addSeparator();
    myFileMenu.add( myFileExitMenuItem );
    myHelpMenu.add( myHelpAboutMenuItem );
  }
}