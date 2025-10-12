export interface Product {
    productUrl: string;
    imageUrl: string;
    title: string;
    percentChange: number;
    currentPrice: number;
}

export interface UserProductCreateDTO {
    userId: string;
    url: string;
}

export interface UserProductDTO {
    trackerId: string;
    priceCheck: number;
    product: Product | null;
}

export interface ItemContextType {
  items: UserProductDTO[];
  loading: boolean;
  fetchItems: () => Promise<void>;
  addItem: (url: string) => Promise<void>;
  //deleteItem: (trackerId: string) => Promise<void>;
}