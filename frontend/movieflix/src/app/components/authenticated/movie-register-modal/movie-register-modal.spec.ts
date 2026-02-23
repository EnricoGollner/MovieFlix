import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieRegisterModal } from './movie-register-modal';

describe('MovieRegisterModal', () => {
  let component: MovieRegisterModal;
  let fixture: ComponentFixture<MovieRegisterModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovieRegisterModal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieRegisterModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
