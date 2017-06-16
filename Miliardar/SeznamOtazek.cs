using System;
using System.Collections.Generic;

namespace Miliardar
{
    public class SeznamOtazek
    {
        private List<Otazka> Otazky;
        private int index = 0;

        public SeznamOtazek()
        {
            Otazky = new List<Otazka>();
        }

        public void Add(string text, string prvni, string druha, string treti, string ctvrta, Otazka.Odpoved spravna)
        {
            Otazky.Add(new Otazka(text, prvni, druha, treti, ctvrta, spravna));
        }

        public bool MoveNext()
        {
            if (index < Otazky.Count - 1)
            {
                index++;
                return true;
            }
            else
            {
                return false;
            }

        }

        public Otazka GetOtazka()
        {
            return Otazky[index];
        }

        public Boolean IsCorrect(Otazka.Odpoved odpoved)
        {
            return odpoved == Otazky[index].Spravna;
        }
    }
}

