import { Component, inject, signal } from '@angular/core';
import { EntrepreneurshipService } from '../../../../services/entrepreneurship.service';
import { EntrepreneurshipCardComponent } from "./entrepreneurship-card/entrepreneurship-card.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-directories-display',
  standalone: true,
  imports: [EntrepreneurshipCardComponent, CommonModule],
  templateUrl: './directories-display.component.html',
  styleUrl: './directories-display.component.scss'
})
export class DirectoriesDisplayComponent {
 private entrepreneurshipService = inject(EntrepreneurshipService);
  
  entrepreneurships = signal<any[]>([]);
  isLoading = signal(true);
  currentSearchQuery = signal('');

  ngOnInit() {
    this.loadEntrepreneurships();
  }

  onSearch(query: string) {
    this.currentSearchQuery.set(query);
    this.loadEntrepreneurships();
  }

  loadEntrepreneurships(page = 0, size = 8) {
    this.isLoading.set(true);
    
    const observable = this.currentSearchQuery() 
      ? this.entrepreneurshipService.searchEntrepreneurships(this.currentSearchQuery(), page, size)
      : this.entrepreneurshipService.getEntrepreneurships(page, size);

    observable.subscribe({
      next: (response) => {
        this.entrepreneurships.set(response.content);
        this.isLoading.set(false);
      },
      error: (err) => {
        console.error('Error:', err);
        this.isLoading.set(false);
      }
    });
  }
}