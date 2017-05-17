using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace PoznamkovacWPF
{
    /// <summary>
    /// Interakční logika pro MainWindow.xaml
    /// </summary>
 
    public partial class MainWindow : Window
    {
        private Databaze databaze = new Databaze();

        public MainWindow()
        {
            InitializeComponent();            
            DataContext = databaze;

            DateTime pom = new DateTime(2017, 5, 16);
            //databaze.PridejZaznam(pom, "Michal");
        }

        private void PridejButton_Click(object sender, RoutedEventArgs e)
        {
            PridejWindow osobaWindow = new PridejWindow(databaze);
            osobaWindow.ShowDialog();          
        }


    }
}
