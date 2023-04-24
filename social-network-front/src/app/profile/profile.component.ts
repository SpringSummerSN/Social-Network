import {Component, OnInit} from '@angular/core';
import {StorageService} from "../_services/storage.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

// ProgileComponent gets current User from Storage using StorageService and show information (username, token, email, roles)
export class ProfileComponent implements OnInit {
  currentUser: any;

  constructor(private storageService: StorageService) {
  }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
  }
}
