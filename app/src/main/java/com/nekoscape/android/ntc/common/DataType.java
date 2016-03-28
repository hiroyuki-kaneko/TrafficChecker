package com.nekoscape.android.ntc.common;

public enum DataType {
	DAY {
		@Override
		public String[] getColumns() {
			return new String[] { COLUMN_HOURS, COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ? and months = ? and days = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_HOURS;
		}
	},
	DAY_SSID {
		@Override
		public String[] getColumns() {
			return new String[] { COLUMN_HOURS, COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, "SUM(mrecv), id" };
		}

		@Override
		public String getSelection() {
			return "years = ? and months = ? and days = ? and id = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_HOURS;
		}
	},DAY_SUM{

		@Override
		public String[] getColumns() {
			return new String[] {COLUMN_DAYS,COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ? and months = ? and days = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_DAYS;
		}
		
	},DAY_EACH{

		@Override
		public String[] getColumns() {
			return new String[] {"id",COLUMN_DAYS,COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ? and months = ? and days = ?";
		}

		@Override
		public String getGroupBy() {
			return "years, months, days, id";
		}
		
	},
	MONTH {
		@Override
		public String[] getColumns() {
			return new String[] { COLUMN_DAYS, COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ? and months = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_DAYS;
		}
	},
	MONTH_SSID {
		@Override
		public String[] getColumns() {
			return new String[] { COLUMN_DAYS, COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ? and months = ? and id = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_DAYS;
		}

	},MONTH_SUM {
		@Override
		public String[] getColumns() {
			return new String[] { COLUMN_MONTH, COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ? and months = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_MONTH;
		}
	},
	YEAR {
		@Override
		public String[] getColumns() {
			return new String[] { COLUMN_MONTH, COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_MONTH;
		}
	},
	YEAR_SSID {
		@Override
		public String[] getColumns() {
			return new String[] { COLUMN_MONTH, COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ? and id = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_MONTH;
		}

	},YEAR_SUM {
		@Override
		public String[] getColumns() {
			return new String[] { COLUMN_YEAR, COLUMN_SUM_OSEND,
					COLUMN_SUM_ORECV, COLUMN_SUM_MSEND, COLUMN_SUM_MRECV };
		}

		@Override
		public String getSelection() {
			return "years = ?";
		}

		@Override
		public String getGroupBy() {
			return GROUPBY_YEAR;
		}
	};

	private static final String COLUMN_HOURS = "hours";
	private static final String COLUMN_SUM_MRECV = "SUM(mrecv)";
	private static final String COLUMN_SUM_MSEND = "SUM(msend)";
	private static final String COLUMN_SUM_ORECV = "SUM(orecv)";
	private static final String COLUMN_SUM_OSEND = "SUM(osend)";
	private static final String COLUMN_DAYS = "days";
	private static final String COLUMN_MONTH = "months";
	private static final String COLUMN_YEAR = "years";
	private static final String GROUPBY_HOURS = "years, months, days, hours";
	private static final String GROUPBY_DAYS = "years, months, days";
	private static final String GROUPBY_MONTH = "years, months";
	private static final String GROUPBY_YEAR = "years";

	abstract public String[] getColumns();

	abstract public String getSelection();

	abstract public String getGroupBy();

}
