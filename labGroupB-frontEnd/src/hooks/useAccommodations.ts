import { useEffect, useState, useCallback } from 'react';
import accommodationApi from '../api/accommodationApi';
import type { DisplayAccommodationDto } from '../api/types/Accommodation';

const useAccommodations = () => {
  const [accommodations, setAccommodations] = useState<DisplayAccommodationDto[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    setLoading(true);

    try {
      const response = await accommodationApi.findAll();
      setAccommodations(response.data);
      setError(null);
    } catch (err) {
      setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  return { accommodations, loading, error, fetch };
};

export default useAccommodations;
