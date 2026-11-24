import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Musee } from '../models';

const apiURL: string = 'http://localhost:8082/api/musees';

@Injectable({
  providedIn: 'root'
})
export class MuseeService {
  apiURL: string = 'http://localhost:8082/api/musees';

  constructor(private http: HttpClient) {}

  listeMusees(): Observable<Musee[]> {
    return this.http.get<Musee[]>(apiURL);
  }

  getAllMusees(): Observable<Musee[]> {
    return this.listeMusees();
  }

  consulterMusee(id: number): Observable<Musee> {
    const url = `${apiURL}/${id}`;
    return this.http.get<Musee>(url);
  }

  getMusee(id: number): Observable<Musee> {
    return this.consulterMusee(id);
  }

  ajouterMusee(musee: Musee): Observable<Musee> {
    return this.http.post<Musee>(apiURL, musee);
  }

  createMusee(musee: Musee): Observable<Musee> {
    return this.ajouterMusee(musee);
  }

  updateMusee(idOrMusee: number | Musee, museeObj?: Musee): Observable<Musee> {
    const id = typeof idOrMusee === 'number' ? idOrMusee : idOrMusee.idMusee;
    const musee = museeObj ? museeObj : (idOrMusee as Musee);
    const url = `${apiURL}/${id}`;
    return this.http.put<Musee>(url, musee);
  }

  supprimerMusee(id: number): Observable<void> {
    const url = `${apiURL}/${id}`;
    return this.http.delete<void>(url);
  }

  deleteMusee(id: number): Observable<void> {
    return this.supprimerMusee(id);
  }

  findByNomMusee(nom: string): Observable<Musee[]> {
    const url = `${apiURL}/nom/${nom}`;
    return this.http.get<Musee[]>(url);
  }
}