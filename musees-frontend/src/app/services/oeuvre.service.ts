import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Oeuvre } from '../models';

const apiURL: string = 'http://localhost:8082/api';

@Injectable({
  providedIn: 'root'
})
export class OeuvreService {
  apiURL: string = 'http://localhost:8082/api';

  constructor(private http: HttpClient) {}

  listeOeuvres(): Observable<Oeuvre[]> {
    return this.http.get<Oeuvre[]>(apiURL + '/all');
  }

  getAllOeuvres(): Observable<Oeuvre[]> {
    return this.listeOeuvres();
  }

  consulterOeuvre(id: number): Observable<Oeuvre> {
    const url = `${apiURL}/getbyid/${id}`;
    return this.http.get<Oeuvre>(url);
  }

  getOeuvre(id: number): Observable<Oeuvre> {
    return this.consulterOeuvre(id);
  }

  ajouterOeuvre(oeuvre: Oeuvre): Observable<Oeuvre> {
    return this.http.post<Oeuvre>(apiURL + '/addoeuv', oeuvre);
  }

  createOeuvre(oeuvre: Oeuvre): Observable<Oeuvre> {
    return this.ajouterOeuvre(oeuvre);
  }

  updateOeuvre(oeuvreOrId: Oeuvre | number, oeuvreObj?: Oeuvre): Observable<Oeuvre> {
    const oeuvre = oeuvreObj ? oeuvreObj : (oeuvreOrId as Oeuvre);
    return this.http.put<Oeuvre>(apiURL + '/updateoeuv', oeuvre);
  }

  supprimerOeuvre(id: number): Observable<void> {
    const url = `${apiURL}/deloeuv/${id}`;
    return this.http.delete<void>(url);
  }

  deleteOeuvre(id: number): Observable<void> {
    return this.supprimerOeuvre(id);
  }

  rechercherParMusee(idMusee: number): Observable<Oeuvre[]> {
    const url = `${apiURL}/oeuvsmusee/${idMusee}`;
    return this.http.get<Oeuvre[]>(url);
  }

  findByMuseeId(idMusee: number): Observable<Oeuvre[]> {
    return this.rechercherParMusee(idMusee);
  }
}