import { useEffect, useState, useCallback } from 'react';
import accommodationApi from '../api/accommodationApi';
import type { DisplayAccommodationDto } from '../api/types/Accommodation';

interface UseAccommodationReturn {
  accommodation: DisplayAccommodationDto | null;
  loading: boolean;
  error: Error | null;
}

const useAccommodation = (id: number): UseAccommodationReturn => {
  const [accommodation, setAccommodation] = useState<DisplayAccommodationDto | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    setLoading(true);
    try {
      const response = await accommodationApi.findById(id);
      setAccommodation(response.data);
      setError(null);
    } catch (err) {
      setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
    } finally {
      setLoading(false);
    }
  }, [id]);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  return { accommodation, loading, error };
};

export default useAccommodation;
