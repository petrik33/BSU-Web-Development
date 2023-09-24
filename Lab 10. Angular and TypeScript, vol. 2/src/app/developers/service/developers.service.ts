import { Time } from '@angular/common';
import { Injectable, inject } from '@angular/core';
import { FirebaseApp } from '@angular/fire/app';
import { Firestore } from '@angular/fire/firestore';
import { DocumentData, QueryDocumentSnapshot, Timestamp, addDoc, collection, deleteDoc, doc, getDoc, getDocs, limit, orderBy, query, setDoc, where } from 'firebase/firestore';

export interface Developer {
  name: string;
  id: string;
  qualification: string;
  salary: number;
}

@Injectable({
  providedIn: 'root'
})
export class DevelopersService {
  private collectionId = 'developers';

  constructor(private db: Firestore) {
    this.db = inject(Firestore);
  }

  private mapDeveloper = (doc: QueryDocumentSnapshot<DocumentData, DocumentData>) => {
    const data = doc.data()
    return {
      ...doc.data(),
      id: doc.id
    } as unknown as Developer
  }

  async getDevelopers() {
    const q = query(
      collection(this.db, this.collectionId),
      orderBy("registered", 'asc')
    );
    const snapshot = await getDocs(q);
    return snapshot.docs.map(this.mapDeveloper);
  }

  async getDeveloper(id: string) {
    const devSnap = await getDoc(doc(this.db, this.collectionId, id))
    return {
      ...devSnap.data(),
      id
    } as unknown as Developer
  }

  async getNextDeveloper(id: string) {
    const devSnap = await getDoc(doc(this.db, this.collectionId, id))
    const data = devSnap.data()
    if (!data) {
      return
    }
    const timestamp : Timestamp = data['registered']

    const q = query(
      collection(this.db, this.collectionId),
      where('registered', '>', timestamp),
      orderBy('registered', 'asc'),
      limit(1)
    );
    const snapshot = await getDocs(q);

    if (snapshot.docs.length === 0) {
      const queryOldest = query(
        collection(this.db, this.collectionId),
        orderBy('registered', 'asc'),
        limit(1)
      );
      const snap3 = await getDocs(queryOldest);
      return snap3.docs.map(this.mapDeveloper)[0];
    }

    return snapshot.docs.map(this.mapDeveloper)[0];
  }

  addDeveloper(developer: Developer) {
    const { id, ...data } = developer
    const doc = {
      ...data,
      registered: Timestamp.now()
    }
    return addDoc(collection(this.db, this.collectionId), doc);
  }

  updateDeveloper(developer: Developer) {
    return setDoc(doc(this.db, this.collectionId, developer.id), developer);
  }

  deleteDeveloper(id: string) {
    return deleteDoc(doc(this.db, this.collectionId, id));
  }
}
