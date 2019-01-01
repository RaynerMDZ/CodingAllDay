import { TestBed } from '@angular/core/testing';

import { PersistentStorageService } from './persistent-storage.service';

describe('PersistentStorageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PersistentStorageService = TestBed.get(PersistentStorageService);
    expect(service).toBeTruthy();
  });
});
