import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { persona } from '../Model/persona.model';


@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  url= environment.URL+'perosnas/';

  constructor(private http: HttpClient) { }

  public getPersona(): Observable<persona>{
    return this.http.get<persona>(this.url+'traer/perfil');
  }
}
