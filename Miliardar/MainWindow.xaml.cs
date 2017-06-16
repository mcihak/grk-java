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

namespace Miliardar
{
    /// <summary>
    /// Interakční logika pro MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private SeznamOtazek otazky = new SeznamOtazek();
        private Otazka aktualniOtazka;

        public MainWindow()
        {
            InitializeComponent();
            otazky.Add("Jakou barvu má tráva?", "červenou", "zelenou", "modrou", "bílou", Otazka.Odpoved.Druha);
            otazky.Add("Kolik nohou má pavouk?", "dvě", "čtyři", "šest", "osm", Otazka.Odpoved.Ctvrta);
            otazky.Add("Kdo vyhrál ligu mistrů v sezóně 2016/2017?", "Bayern Mnichov", "FC Barcelona", "Real Madrid", "Juventus Turín", Otazka.Odpoved.Treti);
            aktualniOtazka = otazky.GetOtazka();
            otazkaTextBlock.Text = aktualniOtazka.Text;
            odpoved1Button.Content = aktualniOtazka.PrvniOdpoved;
            odpoved2Button.Content = aktualniOtazka.DruhaOdpoved;
            odpoved3Button.Content = aktualniOtazka.TretiOdpoved;
            odpoved4Button.Content = aktualniOtazka.CtvrtaOdpoved;
        }

        private void otazka1Button_Click(object sender, RoutedEventArgs e)
        {
            if (aktualniOtazka.Spravna == Otazka.Odpoved.Prvni)
            {
                otazkaTextBlock.Background = Brushes.LightGreen;
            }
            else
            {
                otazkaTextBlock.Background = Brushes.Red;
            }

        }
        private void otazka2Button_Click(object sender, RoutedEventArgs e)
        {
            if (aktualniOtazka.Spravna == Otazka.Odpoved.Druha)
            {
                otazkaTextBlock.Background = Brushes.LightGreen;
            }
            else
            {
                otazkaTextBlock.Background = Brushes.Red;
            }
        }

        private void otazka3Button_Click(object sender, RoutedEventArgs e)
        {
            if (aktualniOtazka.Spravna == Otazka.Odpoved.Treti)
            {
                otazkaTextBlock.Background = Brushes.LightGreen;
            }
            else
            {
                otazkaTextBlock.Background = Brushes.Red;
            }
        }

        private void otazka4Button_Click(object sender, RoutedEventArgs e)
        {
            if (aktualniOtazka.Spravna == Otazka.Odpoved.Ctvrta)
            {
                otazkaTextBlock.Background = Brushes.LightGreen;
            }
            else
            {
                otazkaTextBlock.Background = Brushes.Red;
            }
        }

        private void dalsiButton_Click(object sender, RoutedEventArgs e)
        {
            if (otazky.MoveNext() == true)
            {
                otazkaTextBlock.Background = Brushes.White;
                aktualniOtazka = otazky.GetOtazka();
                otazkaTextBlock.Text = aktualniOtazka.Text;
                odpoved1Button.Content = aktualniOtazka.PrvniOdpoved;
                odpoved2Button.Content = aktualniOtazka.DruhaOdpoved;
                odpoved3Button.Content = aktualniOtazka.TretiOdpoved;
                odpoved4Button.Content = aktualniOtazka.CtvrtaOdpoved;
            }
            else {
                dalsiButton.IsEnabled = false;
                dalsiButton.Content = "Konec";
            }            
        }
    }
}
