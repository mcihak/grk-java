using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PoznamkovacWPF
{
    public class Databaze
    {
        public ObservableCollection<Zaznam> Zaznamy { get; set; }

        public Databaze()
        {
            Zaznamy = new ObservableCollection<Zaznam>();
        }

        public void PridejZaznam(Zaznam z)
        {
            Zaznamy.Add(z);

        }

        public void PridejZaznam(DateTime? datumCas, String text)
        {
            if (text.Length < 3)
                throw new ArgumentException("Záznam je příliš krátký");
            if (datumCas == null)
                throw new ArgumentException("Datum nebylo zadáno");
            if (datumCas.Value.Date < DateTime.Today)
                throw new ArgumentException("Datum nesmí být v minulosti");
            Zaznamy.Add(new Zaznam(datumCas.Value, text));
        }

        public ObservableCollection<Zaznam> NajdiZaznamy(DateTime datumCas)
        {
            ObservableCollection<Zaznam> nalezene = new ObservableCollection<Zaznam>();
            foreach (Zaznam z in Zaznamy)
            {
                if (z.DatumCas == datumCas)
                {
                    nalezene.Add(z);
                }
            }
            return nalezene;
        }

        public void vymazZaznamy(DateTime datumCas)
        {
            ObservableCollection<Zaznam> nalezene = NajdiZaznamy(datumCas);
            foreach (Zaznam z in nalezene)
            {
                Zaznamy.Remove(z);
            }
        }
    }
}

