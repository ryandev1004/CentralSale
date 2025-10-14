import { createContext, useState, useContext, useCallback } from 'react';
import type { ReactNode } from 'react';
import type { UserProductDTO } from '../types/item';
import type { ItemContextType } from '../types/item';
import { itemService } from '../api/itemService';
import { useAuth } from './AuthorizationContext';


export const ItemContext = createContext<ItemContextType | undefined>(undefined);

export const ItemProvider = ({ children }: { children: ReactNode }) => {
    const [items, setItems] = useState<UserProductDTO[]>([]);
    const [loading, setLoading] = useState(false);
    const { user } = useAuth();

    const fetchItems = useCallback( async () => {
        if(!user?.userId) return;

        setLoading(true);
        try {
            const data = await itemService.fetchItems(user?.userId);
            setItems(data);
        } catch (e) {
            console.log('ERROR: ', e);
        } finally {
            setLoading(false);
        }
    }, [user?.userId]);

    const addItem = async (url: string) => {
        if(!user?.userId) return;

        try {
            const newItem = await itemService.createItem(user?.userId, url);
            setItems(prevItem => [...prevItem, newItem]);
        } catch (e) {
            console.log('ERROR: ', e);
            throw e;
        }
    };

    const removeItem = async (trackerId: string) => {
      if(!trackerId) return;

      try {
        await itemService.removeItem(trackerId);
        setItems(prevItems => prevItems.filter(item => item.trackerId !== trackerId));
      } catch (e) {
        console.log('ERROR: ', e);
        throw e;
      }
    }; 

    return (
    <ItemContext.Provider value={{ items, loading, fetchItems, addItem, removeItem }}>
      {children}
    </ItemContext.Provider>
  );

};

export const useItems = () => {
  const context = useContext(ItemContext);
  
  if (context === undefined) {
    throw new Error('useItems must be used within an ItemProvider');
  }
  
  return context;
};