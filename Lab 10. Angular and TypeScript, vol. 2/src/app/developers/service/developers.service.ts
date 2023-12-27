import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';

export interface IDeveloper {
  id: string;
  name: string;
  salary: number;
}

@Injectable({
  providedIn: 'root'
})
export class DevelopersService {
  private apiUrl = 'http://localhost:8082/DemoSpring/devs';

  constructor(private http: HttpClient) {
  }

  getDeveloper(id: string): Observable<IDeveloper> {
    return this.http.get<IDeveloper>(`${this.apiUrl}/${id}`);
  }

  getDevelopers(): Observable<IDeveloper[]> {
    return this.http.get<IDeveloper[]>(this.apiUrl);
  }

  addDeveloper(developer: IDeveloper) {
    console.log('Sending request to:', this.apiUrl);
    console.log('Request body:', developer);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    this.http.post<IDeveloper>(this.apiUrl, developer, httpOptions)
      .subscribe(
        response => console.log('Response:', response),
        error => console.error('Error:', error)
      );
  }

  updateDeveloper(developer: IDeveloper) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.put<IDeveloper>(`${this.apiUrl}/${developer.id}`, developer, httpOptions)
      .subscribe(
        response => console.log('Response:', response),
        error => console.error('Error:', error)
      );
  }

  deleteDeveloper(id: string) {
    this.http.delete(`${this.apiUrl}/${id}`)
      .subscribe(
        response => console.log('Response:', response),
        error => console.error('Error:', error)
      );
  }
}
