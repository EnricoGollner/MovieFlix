export interface Category {
    id: number;
    name: string;
}

export interface StreamingService {
    id: number;
    name: string;
}

export interface Movie {
    id: number;
    title: string;
    description: string;
    releaseDate: string;
    rating: number;
    categories: Category[];
    services: StreamingService[];
} 