import { useEffect, useState, useCallback } from 'react';
import countryApi from '../api/countryApi';
import type { DisplayCountryDto } from '../api/types/Country';

interface UseCountryReturn {
  country: DisplayCountryDto | null;
  loading: boolean;
  error: Error | null;
}

const useCountry = (id: number): UseCountryReturn => {
  const [country, setCountry] = useState<DisplayCountryDto | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    setLoading(true);
    try {
      const response = await countryApi.findById(id);
      setCountry(response.data);
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

  return { country, loading, error };
};

export default useCountry;
