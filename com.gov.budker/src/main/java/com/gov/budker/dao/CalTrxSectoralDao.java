package com.gov.budker.dao;

import com.gov.budker.model.CalTrxSectoral;

import java.math.BigDecimal;
import java.util.List;

public interface CalTrxSectoralDao {

    CalTrxSectoral findById(BigDecimal id);

    void saveCalTrxSectoralEntity(CalTrxSectoral calTrxSectoral);

    public BigDecimal saveOrUpdate(CalTrxSectoral calTrxSectoral);

    void deleteCalTrxSectoralEntity(BigDecimal Id, BigDecimal userDeleted);

    List<CalTrxSectoral> findAllCalTrxSectoralEntity();

    Boolean cekAvailableData(BigDecimal divisionId, String year, BigDecimal triwulan);

    Boolean cekAvailableDataEdit(BigDecimal divisionId, String year, BigDecimal triwulan, BigDecimal Id);

}
