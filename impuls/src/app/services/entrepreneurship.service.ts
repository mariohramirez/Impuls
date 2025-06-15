import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EntrepreneurshipService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `http://localhost:8082/api/v1/public/entrepreneurship`;

  getEntrepreneurships(
    page: number = 0,
    size: number = 5,
    sortBy: string = 'createdAt',
    direction: string = 'desc'
  ): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sortBy', sortBy)
      .set('direction', direction);

    return this.http.get(this.apiUrl, { params });
  }

  searchEntrepreneurships(query: string, page: number = 0, size: number = 8) {
  const params = new HttpParams()
    .set('query', query)
    .set('page', page.toString())
    .set('size', size.toString());

  return this.http.get<any>(`${this.apiUrl}/search`, { params });
}
}