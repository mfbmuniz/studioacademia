export interface PageableObject {
  content ?: [],
  empty ?: boolean,
  first ?: boolean,
  last ?: boolean,
  phone ?: string,
  number ?: number,
  numberOfElements ?: number,
  pageable ?: { },
  size ?: number,
  sort ?: {},
  totalElement ?: number,
  totalPages ?: number
}

export type pageableObject = PageableObject
